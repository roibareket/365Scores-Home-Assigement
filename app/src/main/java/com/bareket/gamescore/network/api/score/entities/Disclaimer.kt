package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class Disclaimer(
    @SerializedName("Text") val text: String,
    @SerializedName("URL") val url: String
)
