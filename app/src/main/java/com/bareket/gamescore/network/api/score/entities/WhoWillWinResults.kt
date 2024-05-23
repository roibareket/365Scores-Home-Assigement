package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class WhoWillWinResults(
    @SerializedName("Vote1") val vote1: Int,
    @SerializedName("VoteX") val voteX: Int,
    @SerializedName("Vote2") val vote2: Int
)
