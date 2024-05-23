package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class Compatitor(
    @SerializedName("ID") val id: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("SymbolicName") val symbolicName: String,
    @SerializedName("NameForURL") val nameForURL: String,
    @SerializedName("CID") val cid: Int,
    @SerializedName("SID") val sid: Int,
    @SerializedName("Color") val color: String,
    @SerializedName("TextColor") val textColor: String,
    @SerializedName("MainComp") val mainComp: Int,
    @SerializedName("CompetitionHasTexture") val competitionHasTexture: Boolean,
    @SerializedName("Trend") val trend: List<Int>,
    @SerializedName("HasSquad") val hasSquad: Boolean,
    @SerializedName("Type") val type: Int,
    @SerializedName("PopularityRank") val popularityRank: Int,
    @SerializedName("ImgVer") val imgVer: Int,
    @SerializedName("ChatUrl") val chatUrl: String? = null
)
