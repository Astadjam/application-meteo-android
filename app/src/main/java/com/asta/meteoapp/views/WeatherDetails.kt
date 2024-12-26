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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.asta.meteoapp.datacontracts.WeatherData

@Composable
fun WeatherDetails(modifier: Modifier=Modifier, weatherdata: WeatherData, isFavorite: MutableState<Boolean>){
    Column(modifier){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(weatherdata.city)
            IconButton(onClick = {
                isFavorite.value = !isFavorite.value
            }) {
                if(!isFavorite.value)
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "")
                else
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "", tint = Color.Red)
            }
        }
    }
}

@Composable
@Preview
fun WeatherDetailsPreview(){
    var isFavorite = remember {mutableStateOf(true)}
    WeatherDetails(
        weatherdata = WeatherData("Ajaccio"),
        isFavorite = isFavorite
    )
}