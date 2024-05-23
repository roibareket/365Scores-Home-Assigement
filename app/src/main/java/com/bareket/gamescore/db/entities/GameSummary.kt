package com.bareket.gamescore.db.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.Date

data class GameSummary(
    val gameSummaryId: Int,
    val competition: Competition,
    val gameScores: List<GameScore>,
    val gameStartDate: Date?
)