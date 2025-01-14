package com.example.composecitylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecitylist.data.CityRepository
import com.example.composecitylist.framework.retrofit.RemoteCityDataSource
import com.example.composecitylist.ui.theme.ComposeCityListTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCityListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Button(onClick = {
        try {
            val repository = CityRepository(RemoteCityDataSource(), RemoteCityDataSource())
            runBlocking { repository.getCountries() }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }) { }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeCityListTheme {
        Greeting("Android")
    }
}