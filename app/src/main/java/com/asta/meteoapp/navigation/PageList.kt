package com.asta.meteoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.asta.meteoapp.views.FavoritePage
import com.asta.meteoapp.views.Home

@Composable
fun PageList(modifier: Modifier=Modifier, navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(route = "Home") {
            Home(modifier = modifier)
        }

        composable(route = "favorite") {
            FavoritePage(modifier = modifier)
        }
    }
}