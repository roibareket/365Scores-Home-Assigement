package com.bareket.gamescore.di

import com.bareket.gamescore.base.App
import com.bareket.gamescore.di.modules.appModule
import com.bareket.gamescore.di.modules.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

fun startKoin(application: App){
    startKoin {
        androidLogger()
        androidContext(application)
        modules(appModule, viewModelsModule)
    }
}