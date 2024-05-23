package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("RangeStart") val rangeStart: String,
    @SerializedName("RangeEnd") val rangeEnd: String,
    @SerializedName("IncludesToday") val includesToday: Boolean,
    @SerializedName("Dates") val dates: List<SummaryDate>
)
