package com.asta.meteoapp.datacontracts

// Data class for weather information
data class WeatherData(
    var city: String?,
    var temperature: Double,
    var temperatureUnit: String,
    var country: String?,
    var maxTemperature: Double?,
    var minTemperature: Double?,
    var windSpeed: Double,
    var weatherCode: Int
)
