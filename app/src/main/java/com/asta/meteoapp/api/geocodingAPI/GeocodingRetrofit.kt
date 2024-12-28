package com.asta.meteoapp.api.geocodingAPI

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofit(context: Context) = Retrofit.Builder()
    .baseUrl("https://geocoding-api.open-meteo.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(GeocodingRequests::class.java)