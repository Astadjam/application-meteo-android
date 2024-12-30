package com.asta.meteoapp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.asta.meteoapp.datacontracts.WeatherData

class DetailsModel : ViewModel() {
    var weatherData: WeatherData? = null

    var isFavorite: MutableState<Boolean> = mutableStateOf(true)
}