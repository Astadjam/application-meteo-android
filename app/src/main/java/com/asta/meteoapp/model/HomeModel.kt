package com.asta.meteoapp.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.asta.meteoapp.datacontracts.WeatherData

class HomeModel: ViewModel() {
    private val input =  mutableStateOf("")
    private val weatherList = mutableStateListOf<WeatherData>()
    private val weatherFavoriteList = mutableStateListOf<WeatherData>()

    fun getInput() = this.input
    fun getWeatherList() = this.weatherList
    fun getWeatherFavoriteList() = this.weatherFavoriteList
}