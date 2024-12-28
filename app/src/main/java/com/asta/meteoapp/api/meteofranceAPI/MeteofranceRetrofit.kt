package com.asta.meteoapp.api.meteofranceAPI

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRetrofit(context: Context) = Retrofit.Builder()
    .baseUrl("https://api.open-meteo.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(MeteofranceRequests::class.java)