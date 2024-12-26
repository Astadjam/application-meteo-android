package com.asta.meteoapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun AppNavigationBar(modifier: Modifier=Modifier, navHostController: NavHostController){
    NavigationBar {
        NavigationBarItem(
            selected = navHostController.currentDestination?.route === "Home",
            onClick = {
                navHostController.navigate(route = "Home")
            },
            icon = {
                Icon( imageVector = Icons.Filled.Home, contentDescription = "")
            },
            label = {
                Text("Home")
            }
        )
        NavigationBarItem(
            selected = navHostController.currentDestination?.route === "favorite",
            onClick = {
                navHostController.navigate(route = "favorite")
            },
            icon = {
                Icon( imageVector = Icons.Filled.Favorite, contentDescription = "")
            },
            label = {
                Text("Favorite")
            }
        )
    }
}