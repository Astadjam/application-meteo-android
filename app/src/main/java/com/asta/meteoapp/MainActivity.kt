package com.asta.meteoapp

import android.os.Bundle
import android.util.Log
import android.view.Display.Mode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.asta.meteoapp.navigation.AppNavigationBar
import com.asta.meteoapp.navigation.PageList
import com.asta.meteoapp.ui.theme.MeteoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MeteoAppTheme {
                var navHostController = rememberNavController()

                Scaffold (bottomBar = {
                    AppNavigationBar(navHostController= navHostController)
                }){
                    PageList(navController = navHostController, modifier = Modifier.padding(it))


                }
            }
        }
    }
}