package com.asta.meteoapp.api.geocodingAPI

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingRequests {
    @GET("search/?language=fr")
    suspend fun getLocationData(@Query("name") city: String): GeocodingData?
}