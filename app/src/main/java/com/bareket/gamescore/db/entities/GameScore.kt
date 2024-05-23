package com.bareket.gamescore.db.entities

import io.objectbox.annotation.Id

data class GameScore(
    val compatitorId: Int,
    val compatitorCountryId: Int,
    val compatitorName: String,
    val compatitorScore: Double,
)