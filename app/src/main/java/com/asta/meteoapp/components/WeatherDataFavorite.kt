package com.asta.meteoapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asta.meteoapp.datacontracts.WeatherData

@Composable
fun weatherDataFavorite(modifier: Modifier=Modifier,
                        weatherData: WeatherData,
                        isInfavorite: MutableState<Boolean>,
                        onClick: ()->Unit){

    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text=weatherData.city ?: "Votre position")
        IconButton(onClick = onClick) {
            if(isInfavorite.value)
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "", tint = Color.Red)
            else
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = "")
        }
    }
}

@Composable
@Preview
fun weatherDataFavoritePreview(){
    var value = remember { mutableStateOf(true) }
    weatherDataFavorite(
        modifier = Modifier.width(300.dp),
        weatherData = WeatherData("Corte",
            13.0,
            "","",13.0,13.0,13.0,"", weatherCode = 1
        ),
        isInfavorite = value,
        onClick = {
            value.value = !value.value
        }
    )
}