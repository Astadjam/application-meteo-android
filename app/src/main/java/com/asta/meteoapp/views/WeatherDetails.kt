package com.asta.meteoapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asta.meteoapp.datacontracts.findDescriptionFromWeatherCode
import com.asta.meteoapp.model.DetailsModel
import com.asta.meteoapp.model.HomeModel

@Composable
fun WeatherDetails(modifier: Modifier=Modifier, detailsModel: DetailsModel= viewModel(),homeModel: HomeModel = viewModel()){
    var weatherData = detailsModel.weatherData.value!!
    Column(modifier){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text="${weatherData.city ?: "Votre position"} ${weatherData.country ?: ""}" )
            IconButton(onClick = {
                if(weatherData.dbId !== null)
                    detailsModel.removeInFavorites()
                else
                    detailsModel.addInFavorites()

                homeModel.refresh.value = true
            }) {
                if(weatherData.dbId == null)
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "")
                else
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "", tint = Color.Red)
            }
        }
        Text("Temperature actuelle : " + weatherData.temperature + weatherData.temperatureUnit)
        Text("Conditions météorologiques : " + findDescriptionFromWeatherCode(weatherData.weatherCode))
        Text("Temperature maximale de la journée : " + weatherData.maxTemperature + weatherData.temperatureUnit)
        Text("Temperature minimale de la journée : " + weatherData.minTemperature + weatherData.temperatureUnit)
        Text("Vitesse du vent : " + weatherData.windSpeed + weatherData.windSpeedUnit)
    }
}

@Composable
@Preview
fun WeatherDetailsPreview(){
    WeatherDetails(
    )
}