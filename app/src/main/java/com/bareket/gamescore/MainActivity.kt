package com.bareket.gamescore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bareket.gamescore.ui.summary.GamesScoreSummaryScreen
import com.bareket.gamescore.ui.summary.GamesScoreSummaryViewModel
import com.bareket.gamescore.ui.common.theme.GameScoreTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val gamesScoreSummaryViewModel by viewModel<GamesScoreSummaryViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScoreTheme {
                // I didn't add it into a fragment as it's not the focus of this exercise
                GamesScoreSummaryScreen(gamesScoreSummaryViewModel)
            }
        }
    }
}

