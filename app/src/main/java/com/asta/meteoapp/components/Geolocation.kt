package com.asta.meteoapp.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.OnTokenCanceledListener

@Composable
fun Geolocation(
    modifier: Modifier,
    context: Context,
    onLocationGet:(Location?) -> Unit,
    onDeny: () -> Unit
){
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted)
            getLastKnownLocation(fusedLocationClient) { location -> onLocationGet(location)}
        else
            onDeny()
    }

    fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            getLastKnownLocation(fusedLocationClient) {location -> onLocationGet(location)}
        else
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    Button(modifier = modifier, onClick = {requestLocationPermission()}) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Me localiser")
            Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Location")
        }
    }
}

@SuppressLint("MissingPermission")
private fun getLastKnownLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationReceived: (Location?) -> Unit
) {
    fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY,object: CancellationToken(){
        override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
            return this
        }

        override fun isCancellationRequested(): Boolean = false

    }).addOnSuccessListener { location: Location? ->
        onLocationReceived(location)
    }
}

@Composable
@Preview
fun GeolocationPreview(){
    Geolocation(
        modifier = Modifier,
        context = LocalContext.current,
        onLocationGet = {location ->
            Log.d("titre","Longitude : ${location?.longitude} - Latitude : ${location?.latitude}")
        },
        onDeny = {
        }
    )
}