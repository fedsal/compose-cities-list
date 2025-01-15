package com.example.composecitylist.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecitylist.domain.City
import kotlinx.coroutines.delay

@Composable
fun CityRow(city: City, onFavouriteClick: () -> Unit = {}, onClick: (city: City) -> Unit = {}) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clickable { onClick(city) }) { // Ensure the Row takes up the height of its tallest child
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    city.countryCode,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text(city.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(Modifier.size(8.dp))
                    Text(
                        "Latitude: ${city.latitude}\nLongitude: ${city.longitude}",
                        fontSize = 12.sp
                    )
                }
                val showLoading = remember { mutableStateOf(false) }
                Button(
                    onClick = {
                        if (!showLoading.value) {
                            onFavouriteClick()
                            showLoading.value = true
                        }
                    },
                    modifier = Modifier.fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    if (showLoading.value) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                        LaunchedEffect(Unit) {
                            delay(1000L)
                            showLoading.value = false
                        }
                    } else {
                        Icon(
                            imageVector = if (city.isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favourite Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            }
        }
    }
}