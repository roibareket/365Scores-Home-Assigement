package com.bareket.gamescore.ui.summary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bareket.gamescore.ui.common.components.ListItemGameSummary
import com.bareket.gamescore.ui.common.components.ListItemHeaderCompetitionType
import com.bareket.gamescore.ui.common.components.ListItemPendingGameSummary
import com.bareket.gamescore.ui.summary.GameSummaryListItem.GameSummaryListItemHeader
import com.bareket.gamescore.ui.summary.GameSummaryListItem.GameSummaryListItemScore
import com.bareket.gamescore.ui.summary.GameSummaryListItem.PendingGameSummaryListItemScore

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GamesScoreSummaryScreen(gamesScoreSummaryViewModel: GamesScoreSummaryViewModel) {
    val hasAccessToUpToDateSummary =
        gamesScoreSummaryViewModel.observeGamesSummaryAvailability().collectAsState(initial = true)
    val gamesSummary = gamesScoreSummaryViewModel.observeGamesSummary().collectAsState(initial = emptyList())
    val listState = rememberLazyListState()
    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {

        if (hasAccessToUpToDateSummary.value.not()) {
            item {
                NotConnectedBar()
            }
        }

        gamesSummary.value.forEach { gameSummary ->
            when (gameSummary) {
                is GameSummaryListItemHeader -> stickyHeader {
                    ListItemHeaderCompetitionType(
                        competitionIconUrl = gameSummary.competitionIconUrl,
                        competitionName = gameSummary.compatitionName
                    )
                }

                is PendingGameSummaryListItemScore -> item {
                    ListItemPendingGameSummary(
                        teamLeftIconUrl = gameSummary.team1IconUrl,
                        teamLeftName = gameSummary.team1Name,
                        teamRightIconUrl = gameSummary.team2IconUrl,
                        teamRightName = gameSummary.team2Name,
                        gameStartTime = gameSummary.startTime
                    )
                }

                is GameSummaryListItemScore -> item {
                    ListItemGameSummary(
                        teamLeftIconUrl = gameSummary.team1IconUrl,
                        teamLeftName = gameSummary.team1Name,
                        teamLeftScore = gameSummary.team1Score,
                        teamRightIconUrl = gameSummary.team2IconUrl,
                        teamRightName = gameSummary.team2Name,
                        teamRightScore = gameSummary.team2Score,
                        gameStatus = gameSummary.gameStatus
                    )
                }

            }


        }

    }
}