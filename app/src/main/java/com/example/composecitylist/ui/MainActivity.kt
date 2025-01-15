package com.example.composecitylist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecitylist.data.CityRepository
import com.example.composecitylist.framework.retrofit.RemoteCityDataSource
import com.example.composecitylist.framework.room.AppDatabase
import com.example.composecitylist.framework.room.LocalCityDataSource
import com.example.composecitylist.ui.screens.cities.CitiesScreen
import com.example.composecitylist.ui.screens.cities.CityViewmodel
import com.example.composecitylist.ui.theme.ComposeCityListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitiesScreen(
                CityViewmodel(
                    CityRepository(
                        remoteDataSource = RemoteCityDataSource(),
                        localDataSource = LocalCityDataSource(cityDao = AppDatabase.getDatabase(this).cityDao())
                    )
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeCityListTheme {
       // Greeting("Android")
    }
}