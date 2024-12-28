package com.asta.meteoapp.api.geocodingAPI

data class LocationData(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val country: String,
    val admin1: String?,
    val admin2: String?,
    val admin3: String?
)

data class GeocodingData(
    val results: List<LocationData>
)