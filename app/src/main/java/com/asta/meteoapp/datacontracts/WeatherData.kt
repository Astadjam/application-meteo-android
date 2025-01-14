package com.asta.meteoapp.datacontracts

import com.asta.meteoapp.database.Weather
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Data class for weather information
@Serializable
data class WeatherData(
    var city: String?,
    var temperature: Double,
    var temperatureUnit: String,
    var country: String?,
    var maxTemperature: Double?,
    var minTemperature: Double?,
    var windSpeed: Double,
    var windSpeedUnit: String,
    var weatherCode: Int,
    var dbId: Int? = null,
    var longitude: Double = 0.0,
    var latitude: Double = 0.0
    ){
    companion object{
        fun fromDatabase(entity: Weather):WeatherData{
            val weatherData = Json.decodeFromString<WeatherData>(entity.weatherdata)
            weatherData.dbId = entity.id

            return weatherData
        }
    }
    fun forDatabase():String= Json.encodeToString(this)
}

    /**
     * @return the description from weather code
     * @attention i used chatgpt to generate the map from open meteo documentation
     */
    fun findDescriptionFromWeatherCode(code: Int): String {
    val weatherDescriptionMap = mapOf(
        "0" to "Ciel clair",
        "1" to "Principalement clair",
        "2" to "Partiellement nuageux",
        "3" to "Couvert",
        "45" to "Brouillard",
        "48" to "Brouillard givrant",
        "51" to "Bruine légère",
        "53" to "Bruine modérée",
        "55" to "Bruine forte",
        "56" to "Bruine verglaçante légère",
        "57" to "Bruine verglaçante forte",
        "61" to "Pluie faible",
        "63" to "Pluie modérée",
        "65" to "Pluie forte",
        "66" to "Pluie verglaçante légère",
        "67" to "Pluie verglaçante forte",
        "71" to "Chute de neige faible",
        "73" to "Chute de neige modérée",
        "75" to "Chute de neige forte",
        "77" to "Grains de neige",
        "80" to "Averses de pluie faibles",
        "81" to "Averses de pluie modérées",
        "82" to "Averses de pluie violentes",
        "85" to "Averses de neige faibles",
        "86" to "Averses de neige fortes",
        "95" to "Orage faible ou modéré",
        "96" to "Orage avec grêle légère",
        "99" to "Orage avec grêle forte"
    )
    return weatherDescriptionMap[code.toString()] ?: "Code inconnu"
}
