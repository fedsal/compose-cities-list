package com.example.composecitylist.ui.screens.cities

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    latitude: Double,
    longitude: Double,
) {
    val coordinates = LatLng(latitude, longitude)
    val markerState = rememberMarkerState(position = coordinates)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coordinates, 12f)
    }

    LaunchedEffect(coordinates) {
        markerState.position = coordinates
        cameraPositionState.position = CameraPosition.fromLatLngZoom(coordinates, 12f)
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = markerState,
        )
    }
}
