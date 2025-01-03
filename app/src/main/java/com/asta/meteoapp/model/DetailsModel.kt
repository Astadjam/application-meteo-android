package com.asta.meteoapp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asta.meteoapp.MainActivity
import com.asta.meteoapp.database.Weather
import com.asta.meteoapp.database.WeatherRepository
import com.asta.meteoapp.datacontracts.WeatherData
import kotlinx.coroutines.launch

class DetailsModel : ViewModel() {
    var weatherData: MutableState<WeatherData?> = mutableStateOf(null)

    fun addInFavorites(){
        if(weatherData.value !== null){
            viewModelScope.launch {
                // add in favorites and get the linked id
                val id = MainActivity.database.getWeatherRepository().addFavorite(
                    Weather(weatherdata = weatherData.value!!.forDatabase())
                )

                // associate favorite id
                weatherData.value = weatherData.value!!.copy(dbId = id.toInt())
            }
        }
    }

    fun removeInFavorites(){
        viewModelScope.launch {
            if(weatherData.value !== null && weatherData.value!!.dbId !== null){
                viewModelScope.launch {
                    MainActivity.database.getWeatherRepository().removeFavorite(id= weatherData.value!!.dbId!!)
                    weatherData.value = weatherData.value!!.copy(dbId = null)
                }
            }
        }
    }
}