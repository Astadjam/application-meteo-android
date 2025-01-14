package com.asta.meteoapp.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asta.meteoapp.MainActivity
import com.asta.meteoapp.api.meteofranceAPI.MeteofranceRequests
import com.asta.meteoapp.database.Weather
import com.asta.meteoapp.database.WeatherRepository
import com.asta.meteoapp.datacontracts.WeatherData
import kotlinx.coroutines.launch

class DetailsModel : ViewModel() {
    var weatherData: MutableState<WeatherData?> = mutableStateOf(null)
    private var meteofranceRequests: MeteofranceRequests?=null

    fun reloadData(context: Context){
        if(meteofranceRequests === null){
            meteofranceRequests = com.asta.meteoapp.api.meteofranceAPI.createRetrofit(context)
        }

        try{
            viewModelScope.launch {
                val result = meteofranceRequests!!.getWeather(longitude = weatherData.value!!.longitude, latitude = weatherData.value!!.latitude)

                if(result !== null){
                    val resultConvertion = result.convertToWeatherData()

                    resultConvertion.dbId = weatherData.value!!.dbId

                    weatherData.value = resultConvertion
                }
            }
        }
        catch (_:Exception){}
    }

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