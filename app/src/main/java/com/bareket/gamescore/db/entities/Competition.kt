package com.bareket.gamescore.db.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class Competition(
    val competitionId: Int,
    val countryId: Int,
    val competitionName: String,
    )
