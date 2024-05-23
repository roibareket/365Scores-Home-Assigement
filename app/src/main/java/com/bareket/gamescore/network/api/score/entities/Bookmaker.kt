package com.bareket.gamescore.network.api.score.entities

import com.google.gson.annotations.SerializedName

data class Bookmaker(
    @SerializedName("ID") val id: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("URL") val url: String,
    @SerializedName("Offers") val offers: List<Any>,
    @SerializedName("ShowLinksInBrowser") val showLinksInBrowser: Boolean,
    @SerializedName("UseDeepestLink") val useDeepestLink: Boolean,
    @SerializedName("Sponsored") val sponsored: Boolean,
    @SerializedName("ActionButton") val actionButton: ActionButton,
    @SerializedName("Disclaimer") val disclaimer: Disclaimer,
    @SerializedName("NameForURL") val nameForURL: String,
    @SerializedName("ImgVer") val imgVer: Int,
    @SerializedName("Color") val color: String,
    @SerializedName("SecondaryColor") val secondaryColor: String,
    @SerializedName("PromotionTextColor") val promotionTextColor: String,
    @SerializedName("CTATextColor") val ctaTextColor: String,
    @SerializedName("CTABGColor") val ctaBgColor: String,
    @SerializedName("LiveColor") val liveColor: String,
    @SerializedName("GeneralTextColor") val generalTextColor: String
)
