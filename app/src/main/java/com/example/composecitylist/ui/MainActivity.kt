package com.example.composecitylist.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composecitylist.domain.City
import com.example.composecitylist.ui.screens.cities.CitiesScreen
import com.example.composecitylist.ui.screens.cities.MapScreen
import com.example.composecitylist.ui.screens.navigation.Destinations
import com.example.composecitylist.ui.screens.navigation.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val configuration = LocalConfiguration.current

            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LandscapeDesign()
            } else {
                PortraitDesign()
            }
        }
    }
}


@Composable
fun PortraitDesign() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBarWithBackButton(navController)
        }
    ) { innerPadding ->
        NavigationHost(
            Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

@Composable
fun LandscapeDesign() {
    Row {
        val city = remember { mutableStateOf(City(1, "", "", 1.0, 1.0)) }
        CitiesScreen(
            modifier = Modifier.weight(1f),
            onCityClicked = { city.value = it }
        )

        MapScreen(Modifier.weight(1f), city.value.latitude, city.value.longitude)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val canNavigateBack = currentBackStackEntry?.destination?.route != Destinations.CITIES.name

    TopAppBar(
        title = { Text("Compose cities") },
        navigationIcon =
        {
            if (canNavigateBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}