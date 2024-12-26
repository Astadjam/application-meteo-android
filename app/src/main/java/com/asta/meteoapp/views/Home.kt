package com.asta.meteoapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.asta.meteoapp.components.Geolocation
import com.asta.meteoapp.components.searchBar
import com.asta.meteoapp.datacontracts.WeatherData
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.asta.meteoapp.components.weatherData
import com.asta.meteoapp.components.weatherDataFavorite

@Composable
fun Home(modifier: Modifier=Modifier){
    var input = remember { mutableStateOf("") }
    var weatherList = remember { mutableStateListOf<WeatherData>(WeatherData("Corte"), WeatherData("Corte")) }
    var weatherFavoriteList = remember { mutableStateListOf<WeatherData>(WeatherData("Corte"), WeatherData("Corte")) }
    Column(modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
        searchBar(
            inputState = input,
            onValueChange = {
                input.value = it
            },
            onClick = {
            }
        )
        Geolocation(
            modifier = Modifier,
            context = LocalContext.current,
            onLocationGet = {location ->
            },
            onDeny = {
            }
        )

        if(weatherList.isNotEmpty()){
            Text("Résultats")
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=weatherList){ item ->
                    weatherData(
                        modifier = Modifier.fillMaxWidth(),
                        weatherData = item
                    )
                }
            }
        }

        if(weatherFavoriteList.isNotEmpty()){
            Text("Favoris")
            LazyColumn {
                items(items=weatherFavoriteList){ item ->
                    var value = remember { mutableStateOf(true) }
                    weatherDataFavorite(
                        modifier = Modifier.fillMaxWidth(),
                        weatherData = item,
                        isInfavorite = value,
                        onClick = {
                            value.value = !value.value
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun HomePreview(){
    Home()
}