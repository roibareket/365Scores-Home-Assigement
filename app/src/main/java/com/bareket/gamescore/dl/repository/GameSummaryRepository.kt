package com.bareket.gamescore.dl.repository

import android.util.Log
import com.bareket.gamescore.db.entities.Competition
import com.bareket.gamescore.db.entities.GameScore
import com.bareket.gamescore.db.entities.GameSummary
import com.bareket.gamescore.network.api.score.ScoresApi
import com.bareket.gamescore.network.api.score.entities.Compatitor
import com.bareket.gamescore.network.api.score.entities.GamesSummary
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class GameSummaryRepository(private val scoresApi: ScoresApi) {

    // can and should be replaced with a database.
    private val mutableSummary = MutableStateFlow<Map<Int, GameSummary>>(emptyMap())

    // should be saved in sharedPrefs with the ttl, so we could know to clear outdated results
    // on every app launch and support offline
    var lastUpdateID: Long? = null

    fun pollGameSummary(pollingTimeout: Long = 5000): Flow<Boolean> =
        flow {
            while (true) {
                emit(Unit)
                delay(pollingTimeout)
            }
        }.map {
            runCatching {
                lastUpdateID.let { updateId ->
                    if (updateId != null) {
                        scoresApi.getGamesScoreSummaryDiff(lastUpdateID = updateId)
                    } else {
                        scoresApi.getGamesScoreSummary()
                    }
                }
            }.onFailure {
                Log.e("GameSummaryRepository", "failed to load scores", it)
            }

                .getOrNull()
        }
            .onEach { summary -> saveSummary(summary) }
            .map { summary -> summary != null }

    fun observeGameSummary() = mutableSummary.map { it.values.toList() }

    private fun saveSummary(newGamesSummary: GamesSummary?) {
        if (newGamesSummary == null) return

        lastUpdateID = newGamesSummary.lastUpdateID

        mutableSummary.update { currentSummary ->
            currentSummary.toMutableMap().also { updatedSummary ->

                val competitions = currentSummary.values.map { it.competition }
                    .plus(newGamesSummary.competitions.orEmpty().map {
                        Competition(
                            it.id,
                            it.countryId,
                            it.name
                        )
                    })
                    .associateBy { it.competitionId }

                newGamesSummary.games?.forEach { game ->
                    val scores = game.comps.orEmpty().mapIndexed { index: Int, compatitor: Compatitor ->
                        GameScore(
                            compatitorId = compatitor.id,
                            compatitorCountryId = compatitor.cid,
                            compatitorName = compatitor.name,
                            compatitorScore = game.scores[index]
                        )
                    }

                    competitions[game.comp]?.let { competition ->
                        GameSummary(
                            gameSummaryId = game.id,
                            competition = competition,
                            gameScores = scores,
                            gameStartDate = game.sTime
                        )
                    }?.let { updatedGame ->
                        updatedSummary.put(game.id, updatedGame)
                    }
                }
            }
        }
    }
}