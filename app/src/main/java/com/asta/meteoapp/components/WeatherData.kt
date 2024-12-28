package com.asta.meteoapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asta.meteoapp.datacontracts.WeatherData

@Composable
fun weatherData(modifier: Modifier=Modifier, weatherData: WeatherData){
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text=weatherData.city ?: "")
        Icon(imageVector = Icons.Filled.Info, contentDescription = "")
    }
}

@Composable
@Preview
fun weatherDataPreview(){
    weatherData(
        modifier = Modifier.width(300.dp),
        weatherData = WeatherData("Corte",
            13.0,
            "","",13.0,13.0,13.0,1
        )
    )
}