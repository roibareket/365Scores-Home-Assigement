package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class SocialStats(
    @SerializedName("Comments") val comments: Int
)
