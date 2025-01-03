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

    fun loadFavorites(){
        viewModelScope.launch {
            weatherFavoriteList.clear()
            weatherFavoriteList.addAll(
                MainActivity.database.getWeatherRepository().getAllFavorite().map { WeatherData.fromDatabase(entity = it) }
            )
        }
    }

    fun getWeatherFavoriteList() = this.weatherFavoriteList
}