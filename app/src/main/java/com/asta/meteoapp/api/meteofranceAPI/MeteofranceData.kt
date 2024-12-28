package com.asta.meteoapp.api.meteofranceAPI

/**
 * @brief open weather dto
 */
data class MeteofranceData(
    val longitude: Double,
    val latitude: Double,
    val current_units: CurrentUnits,
    val daily_units: DailyUnits,
    val current: Current,
    val daily: Daily
)

/**
 * @brief open weather current units
 */
data class CurrentUnits(
    val temperature_2m: String,
    val wind_speed_10m: String
)

/**
 * @brief open weather daily units
 */
data class DailyUnits(
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val wind_speed_10m_max: String
)

/**
 * @brief current value dto
 */
data class Current(
    val temperature_2m: Double,
    val weather_code: Int,
    val wind_speed_10m: Double
)

/**
 * @brief daily value dto
 */
data class Daily(
    val time: List<String>,
    val weather_code: List<Int?>,
    val temperature_2m_max: List<Double?>,
    val temperature_2m_min: List<Double?>,
    val wind_speed_10m_max: List<Double?>,
)