package com.bareket.gamescore.di.modules

import com.bareket.gamescore.dl.repository.GameSummaryRepository
import com.bareket.gamescore.network.api.score.ScoresApi
import com.bareket.gamescore.network.api.score.newRetrofit
import org.koin.dsl.module

val appModule = module {
    single<ScoresApi> { newRetrofit(ScoresApi.BASE_URL_PROD).create(ScoresApi::class.java) }
    single { getObject(::GameSummaryRepository) }
}