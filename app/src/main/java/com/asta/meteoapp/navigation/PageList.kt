package com.asta.meteoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.asta.meteoapp.model.HomeModel
import com.asta.meteoapp.views.FavoritePage
import com.asta.meteoapp.views.Home

@Composable
fun PageList(modifier: Modifier=Modifier, navController: NavHostController){
    val homeModel = viewModel<HomeModel>()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){

        composable(route = "Home") {
            Home(modifier = modifier, homeModel = homeModel)
        }

        composable(route = "favorite") {
            FavoritePage(modifier = modifier)
        }
    }
}