package com.bareket.gamescore.ui.summary

import androidx.lifecycle.ViewModel
import com.bareket.gamescore.db.entities.Competition
import com.bareket.gamescore.db.entities.GameSummary
import com.bareket.gamescore.dl.repository.GameSummaryRepository
import com.bareket.gamescore.network.api.score.ScoresApi
import com.bareket.gamescore.ui.summary.GameSummaryListItem.GameSummaryListItemHeader
import com.bareket.gamescore.ui.summary.GameSummaryListItem.GameSummaryListItemScore
import com.bareket.gamescore.ui.summary.GameSummaryListItem.PendingGameSummaryListItemScore
import java.util.Date
import kotlinx.coroutines.flow.map

class GamesScoreSummaryViewModel(
    private val gameSummaryRepository: GameSummaryRepository
) : ViewModel() {
    fun observeGamesSummaryAvailability() = gameSummaryRepository.pollGameSummary()

    fun observeGamesSummary() = gameSummaryRepository.observeGameSummary()
        .map { gamesSummary -> gamesSummary.toListItems() }
}

private fun List<GameSummary>.toListItems(): MutableList<GameSummaryListItem> {
    val sortedByCompetitionSummary = sortedBy { summary -> summary.competition.competitionName }
    val listItems = mutableListOf<GameSummaryListItem>()

    var competition: Competition? = null
    sortedByCompetitionSummary.forEach { summary ->
        if (competition != summary.competition) {
            competition = summary.competition
            listItems.add(
                GameSummaryListItemHeader(
                    competitionIconUrl = ScoresApi.getCompetitionUrlPath(summary.competition.countryId),
                    compatitionName = summary.competition.competitionName
                )
            )
        }

        val competitor0 = summary.gameScores.getOrNull(0) ?: return@forEach
        val competitor1 = summary.gameScores.getOrNull(1) ?: return@forEach

        listItems.add(
            if (competitor0.compatitorScore == -1.0 && competitor1.compatitorScore == -1.0) {
                PendingGameSummaryListItemScore(
                    team1IconUrl = ScoresApi.getCompetitorUrlPath(competitor0.compatitorId),
                    team1Name = competitor0.compatitorName,
                    team2IconUrl = ScoresApi.getCompetitorUrlPath(competitor1.compatitorId),
                    team2Name = competitor1.compatitorName,
                    startTime = summary.gameStartDate
                )
            } else {

                GameSummaryListItemScore(
                    team1IconUrl = ScoresApi.getCompetitorUrlPath(competitor0.compatitorId),
                    team1Name = competitor0.compatitorName,
                    team1Score = competitor0.compatitorScore.toInt(),
                    team2IconUrl = ScoresApi.getCompetitorUrlPath(competitor1.compatitorId),
                    team2Name = competitor1.compatitorName,
                    team2Score = competitor1.compatitorScore.toInt(),
                    gameStatus = "ended" // how do i get this value?
                )
            }
        )
    }
    return listItems
}

sealed interface GameSummaryListItem {
    data class GameSummaryListItemHeader(
        val competitionIconUrl: String,
        val compatitionName: String
    ) : GameSummaryListItem

    data class GameSummaryListItemScore(
        val team1IconUrl: String,
        val team1Name: String,
        val team1Score: Int,
        val team2IconUrl: String,
        val team2Name: String,
        val team2Score: Int,
        val gameStatus: String
    ) : GameSummaryListItem

    data class PendingGameSummaryListItemScore(
        val team1IconUrl: String,
        val team1Name: String,
        val team2IconUrl: String,
        val team2Name: String,
        val startTime: Date?
    ) : GameSummaryListItem
}