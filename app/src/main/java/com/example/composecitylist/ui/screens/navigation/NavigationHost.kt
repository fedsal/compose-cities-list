package com.example.composecitylist.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composecitylist.ui.screens.cities.CitiesScreen
import com.example.composecitylist.ui.screens.cities.MapScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(modifier = modifier, navController = navController, startDestination = Destinations.CITIES.name) {
        composable(route = Destinations.CITIES.name) {
            CitiesScreen(
                onCityClicked = { city -> navController.navigate("${Destinations.MAP}/${city.latitude}/${city.longitude}") }
            )
        }
        composable(
            route = "${Destinations.MAP.name}/{latitude}/{longitude}",
            arguments = listOf(
                navArgument("latitude") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("longitude") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            val latitude = it.arguments?.getString("latitude")
            val longitude = it.arguments?.getString("longitude")
            MapScreen(latitude = latitude?.toDouble() ?: 0.0, longitude = longitude?.toDouble() ?: 0.0)
        }
    }
}