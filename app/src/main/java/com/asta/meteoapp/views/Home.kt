package com.asta.meteoapp.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.asta.meteoapp.components.weatherData
import com.asta.meteoapp.components.weatherDataFavorite
import com.asta.meteoapp.model.DetailsModel
import com.asta.meteoapp.model.HomeModel

@Composable
fun Home(modifier: Modifier=Modifier, homeModel: HomeModel = viewModel(), detailsModel: DetailsModel= viewModel(), context: Context= LocalContext.current, onDetailsClick:() ->Unit){
    var input = homeModel.getInput()
    var weatherList = homeModel.getWeatherList()
    var weatherFavoriteList = homeModel.getWeatherFavoriteList()
    Column(modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
        searchBar(
            inputState = input,
            onValueChange = {
                input.value = it
                homeModel.searchFromInput(it, context)
            },
            onClick = {
            }
        )
        Geolocation(
            modifier = Modifier,
            context = LocalContext.current,
            onLocationGet = {location ->
                Log.d("Hello", location?.toString() ?: "null")
                if(location !== null){
                    homeModel.searchFromGeolocation(location.longitude, location.latitude, context)
                }
            },
            onDeny = {
                Log.d("Hello", "erreur")
            }
        )

        if(weatherList.isNotEmpty()){
            Text("Résultats")
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items=weatherList){ item ->
                    weatherData(
                        modifier = Modifier.fillMaxWidth().clickable {
                            detailsModel.weatherData = item
                            onDetailsClick()
                        },
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
    Home(onDetailsClick = {})
}