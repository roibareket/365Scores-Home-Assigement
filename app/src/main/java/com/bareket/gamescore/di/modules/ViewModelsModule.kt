package com.bareket.gamescore.di.modules

import com.bareket.gamescore.ui.summary.GamesScoreSummaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { getObject(::GamesScoreSummaryViewModel) }
}