package com.asta.meteoapp.api.geocodingAPI

import android.content.Context
import com.asta.meteoapp.api.CacheConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofit(context: Context) = Retrofit.Builder()
    .baseUrl("https://geocoding-api.open-meteo.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(CacheConfig.buildHttpClient(context = context, apiName = "geocoding"))
    .build()
    .create(GeocodingRequests::class.java)