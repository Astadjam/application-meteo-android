package com.asta.meteoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.asta.meteoapp.model.DetailsModel
import com.asta.meteoapp.model.HomeModel
import com.asta.meteoapp.views.FavoritePage
import com.asta.meteoapp.views.Home
import com.asta.meteoapp.views.WeatherDetails

@Composable
fun PageList(modifier: Modifier=Modifier, navController: NavHostController){
    val homeModel = viewModel<HomeModel>()
    val detailsModel = viewModel<DetailsModel>()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){

        composable(route = "Home") {
            Home(modifier = modifier, homeModel = homeModel, detailsModel = detailsModel, onDetailsClick = {
                navController.navigate(route = "details")
            })
        }

        composable(route = "favorite") {
            FavoritePage(modifier = modifier,homeModel = homeModel)
        }

        composable(route = "details") {
            WeatherDetails(modifier = modifier, detailsModel = detailsModel,homeModel=homeModel)
        }
    }
}