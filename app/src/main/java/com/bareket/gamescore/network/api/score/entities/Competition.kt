package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class Competition(
    @SerializedName("ID") val id: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("CID") val countryId: Int,
)
