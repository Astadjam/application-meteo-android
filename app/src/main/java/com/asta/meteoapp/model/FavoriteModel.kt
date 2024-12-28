package com.asta.meteoapp.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.asta.meteoapp.datacontracts.WeatherData

class FavoriteModel: ViewModel() {
    private val weatherFavoriteList = mutableStateListOf<WeatherData>()

    fun getWeatherFavoriteList() = this.weatherFavoriteList
}