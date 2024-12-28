package com.asta.meteoapp.api.meteofranceAPI

import retrofit2.http.GET
import retrofit2.http.Query

interface MeteofranceRequests {
    @GET("forecast/?current=temperature_2m,weather_code,wind_speed_10m&daily=temperature_2m_max,temperature_2m_min&forecast_days=1&models=meteofrance_seamless")
    suspend fun getWeather(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double
    ): MeteofranceData?
}
