package com.asta.meteoapp.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asta.meteoapp.api.geocodingAPI.createRetrofit
import com.asta.meteoapp.datacontracts.WeatherData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeModel: ViewModel() {
    private val input =  mutableStateOf("")
    private val weatherList = mutableStateListOf<WeatherData>()
    private val weatherFavoriteList = mutableStateListOf<WeatherData>()
    private var task: Job? = null

    fun getInput() = this.input
    fun getWeatherList() = this.weatherList
    fun getWeatherFavoriteList() = this.weatherFavoriteList

    fun searchFromInput(input: String, context: Context){
        task?.cancel()
        task = viewModelScope.launch {
            delay(300)
            try {
                var resultsSearchFromInput = createRetrofit(context).getLocationData(input)
            }catch (_:Exception){}
        }
    }
}