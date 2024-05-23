package com.bareket.gamescore.base

import android.app.Application
import com.bareket.gamescore.di.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this)
    }
}