package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class GamesSummary(
    @SerializedName("LastUpdateID") val lastUpdateID: Long,
    @SerializedName("Games") val games: List<GameSummary>?,
    @SerializedName("Competitions") val competitions: List<Competition>?,

    @SerializedName("Bookmakers") val bookmakers: List<Bookmaker>,
    @SerializedName("CurrentDate") val currentDate: String,
    @SerializedName("CurrentTimeUTC") val currentTimeUTC: String,
    @SerializedName("TTL") val ttl: Int,
    @SerializedName("ScrollIndex") val scrollIndex: Int,
    @SerializedName("Summary") val summary: Summary
)