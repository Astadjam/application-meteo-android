package com.asta.meteoapp.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asta.meteoapp.MainActivity
import com.asta.meteoapp.datacontracts.WeatherData
import kotlinx.coroutines.launch

class FavoriteModel: ViewModel() {
    private val weatherFavoriteList = mutableStateListOf<WeatherData>()

    init{
        loadFavorites()
    }

    fun loadFavorites(){
        viewModelScope.launch {
            weatherFavoriteList.clear()
            weatherFavoriteList.addAll(
                MainActivity.database.getWeatherRepository().getAllFavorite().map { WeatherData.fromDatabase(entity = it) }
            )
        }
    }

    fun removeInFavorite(weatherData: WeatherData){
        if(weatherData.dbId !== null){
            viewModelScope.launch {
                MainActivity.database.getWeatherRepository().removeFavorite(id= weatherData.dbId!!)
                weatherFavoriteList.remove(weatherData)
            }
        }
    }

    fun getWeatherFavoriteList() = this.weatherFavoriteList
}