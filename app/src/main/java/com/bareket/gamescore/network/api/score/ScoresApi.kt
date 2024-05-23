package com.bareket.gamescore.network.api.score

import com.bareket.gamescore.network.api.score.entities.GamesSummary
import retrofit2.http.GET
import retrofit2.http.Query

interface ScoresApi {

    @GET("/Data/Games/?lang=1&uc=6&tz=15&countries=1")
    suspend fun getGamesScoreSummary(): GamesSummary

    @GET("/Data/Games/?lang=1&uc=6&tz=15&countries=1")
    suspend fun getGamesScoreSummaryDiff(@Query("UID") lastUpdateID: Long): GamesSummary

    companion object {
        const val BASE_URL_PROD = "https://mobilews.365scores.com/"

        fun getCompetitionUrlPath(competitionId: Int) =
            "http://imagescache.365scores.com/image/upload/w_140,h_140,c_limit,f_webp,q_90,d_Countries:Round:default.png/Countries/Round/$competitionId"

        fun getCompetitorUrlPath(compatitorId: Int) =
            "https://imagescache.365scores.com/image/upload/w_48,h_48,c_limit,f_webp,q_90,d_Competitors:default1.png/Competitors/$compatitorId"
    }
}
