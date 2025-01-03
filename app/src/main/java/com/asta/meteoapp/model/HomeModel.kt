package com.asta.meteoapp.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asta.meteoapp.MainActivity
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
    private var message: MutableState<String?> = mutableStateOf(null)
    val refresh = mutableStateOf(true)

    fun removeInFavorite(weatherData: WeatherData){
        if(weatherData.dbId !== null){
            viewModelScope.launch {
                MainActivity.database.getWeatherRepository().removeFavorite(id= weatherData.dbId!!)
                weatherFavoriteList.remove(weatherData)
            }
        }
    }

    fun loadSomeFavorites(){
        viewModelScope.launch{
            weatherFavoriteList.clear()
            weatherFavoriteList.addAll(MainActivity.database.getWeatherRepository().loadSome().map { WeatherData.fromDatabase(entity = it) })
        }
    }
    fun getInput() = this.input
    fun getWeatherList() = this.weatherList
    fun getWeatherFavoriteList() = this.weatherFavoriteList
    fun getMessage() = this.message

    fun searchFromInput(input: String, context: Context){
        try {
            if(geocodingRequests === null){
                geocodingRequests = com.asta.meteoapp.api.geocodingAPI.createRetrofit(context)
            }
            if(meteofranceRequests === null){
                meteofranceRequests = com.asta.meteoapp.api.meteofranceAPI.createRetrofit(context)
            }
            weatherList.clear()
            task?.cancel()
            task = viewModelScope.launch {
                delay(1000)
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
                }catch (e:Exception){
                    message.value = "Erreur reseau!"
                }
            }

        }catch (e:Exception){
            message.value = "Une erreur interne s'est produite, veuillez réessayer!"
        }

    }

    fun searchFromGeolocation(longitude: Double, latitude: Double, context: Context){
        try {
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
                    message.value = "Erreur reseau!"
                }
            }

        }catch (e: Exception){

            message.value = "Une erreur interne s'est produite, veuillez réessayer!"
        }
    }
}