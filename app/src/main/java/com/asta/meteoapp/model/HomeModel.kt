package com.asta.meteoapp.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asta.meteoapp.api.geocodingAPI.GeocodingRequests
import com.asta.meteoapp.api.meteofranceAPI.MeteofranceRequests
import com.asta.meteoapp.datacontracts.WeatherData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeModel: ViewModel() {
    private val input =  mutableStateOf("")
    private val weatherList = mutableStateListOf<WeatherData>()
    private val weatherFavoriteList = mutableStateListOf<WeatherData>()
    private var task: Job? = null
    private var geocodingRequests: GeocodingRequests?=null
    private var meteofranceRequests: MeteofranceRequests?=null

    fun getInput() = this.input
    fun getWeatherList() = this.weatherList
    fun getWeatherFavoriteList() = this.weatherFavoriteList

    fun searchFromInput(input: String, context: Context){
        if(geocodingRequests === null){
            geocodingRequests = com.asta.meteoapp.api.geocodingAPI.createRetrofit(context)
        }
        if(meteofranceRequests === null){
            meteofranceRequests = com.asta.meteoapp.api.meteofranceAPI.createRetrofit(context)
        }
        weatherList.clear()
        task?.cancel()
        task = viewModelScope.launch {
            delay(300)
            try {
                var resultsSearchFromInput = geocodingRequests!!.getLocationData(input)

                if(resultsSearchFromInput !== null){
                    var resultWeatherDataList = resultsSearchFromInput.results.mapNotNull {
                        var weatherData = meteofranceRequests!!.getWeather(it.longitude, it.latitude)?.convertToWeatherData()

                        weatherData?.city=it.name
                        weatherData?.country=it.country

                        weatherData
                    }

                    weatherList.addAll(resultWeatherDataList)
                }
            }catch (_:Exception){}
        }
    }

    fun searchFromGeolocation(longitude: Double, latitude: Double, context: Context){
        if(meteofranceRequests === null){
            meteofranceRequests = com.asta.meteoapp.api.meteofranceAPI.createRetrofit(context)
        }
        weatherList.clear()
        task?.cancel()
        task = viewModelScope.launch {
            delay(300)
            try {
                var weatherData = meteofranceRequests!!.getWeather(longitude, latitude)
                if(weatherData !== null){
                    weatherList.add(weatherData.convertToWeatherData())
                }
            }catch (e:Exception){
                Log.d("Hello",e.toString())
            }
        }
    }
}