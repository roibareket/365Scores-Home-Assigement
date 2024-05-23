package com.bareket.gamescore.ui.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bareket.gamescore.R
import com.bareket.gamescore.ui.common.toPrettyDate
import java.util.Date

@Composable
fun ListItemGameSummary(
    teamLeftIconUrl: String,
    teamLeftName: String,
    teamLeftScore: Int,
    teamRightIconUrl: String,
    teamRightName: String,
    teamRightScore: Int,
    gameStatus: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TeamDetailsLeft(teamLeftIconUrl, teamLeftName, modifier = Modifier.weight(1f))
        GameSummaryDetails(teamLeftScore, teamRightScore, gameStatus)
        TeamDetailsRight(teamRightIconUrl, teamRightName, modifier = Modifier.weight(1f))
    }
}

@Composable
fun ListItemPendingGameSummary(
    teamLeftIconUrl: String,
    teamLeftName: String,
    gameStartTime: Date?,
    teamRightIconUrl: String,
    teamRightName: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TeamDetailsLeft(teamLeftIconUrl, teamLeftName, modifier = Modifier.weight(1f))
        PendingGameSummaryDetails(gameStartTime)
        TeamDetailsRight(teamRightIconUrl, teamRightName, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun TeamDetailsLeft(
    teamIconUrl: String,
    teamName: String,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        AsyncImage(
            model = teamIconUrl,
            contentDescription = null,
            modifier = Modifier.size(32.dp),

            )
        Text(
            text = teamName,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun TeamDetailsRight(
    teamIconUrl: String,
    teamName: String,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {

        Text(
            text = teamName,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.padding(end = 8.dp)
                .weight(1f),
            textAlign = TextAlign.End
        )
        AsyncImage(
            model = teamIconUrl,
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
private fun GameSummaryDetails(
    leftTeamScore: Int,
    rightTeamScore: Int,
    gameStatus: String,
) {
    Column(
        modifier = Modifier
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(id = R.string.score_summary, leftTeamScore, rightTeamScore),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,

            )

        Text(
            text = gameStatus,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
        )
    }
}

@Composable
private fun PendingGameSummaryDetails(
    gameStartTime: Date?,
) {
    Text(
        text = gameStartTime?.toPrettyDate() ?: "---",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Black,
        modifier = Modifier
            .wrapContentHeight(),
        )
}

@Preview
@Composable
fun previewListItemGameSummary() {
    ListItemGameSummary(
        teamLeftIconUrl = "http://imagescache.365scores.com/image/upload/w_48,h_48,c_limit,f_webp,q_90,d_Competitors:default1.png/Competitors/183",
        teamLeftName = "Israel",
        teamLeftScore = 1,
        teamRightIconUrl = "http://imagescache.365scores.com/image/upload/w_48,h_48,c_limit,f_webp,q_90,d_Competitors:default1.png/Competitors/183",
        teamRightName = "Germany is a very super country",
        teamRightScore = 2,
        gameStatus = "ended"
    )
}