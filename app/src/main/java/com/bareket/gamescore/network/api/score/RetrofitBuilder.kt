package com.bareket.gamescore.network.api.score

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun newRetrofit(baseUrl: String) = Retrofit.Builder()
    .client(
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(BASIC))
            .build()
    )
    .baseUrl(baseUrl)
    .addConverterFactory(newRetrofitGsonFactory())
    .build()

private fun newRetrofitGsonFactory() =
    GsonConverterFactory.create(
        GsonBuilder()
            .setDateFormat("dd-MM-yyyy HH:mm").create()
    )