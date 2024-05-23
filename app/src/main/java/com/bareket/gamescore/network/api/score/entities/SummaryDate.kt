package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class SummaryDate(
    @SerializedName("Date") val date: String,
    @SerializedName("Count") val count: Int
)
