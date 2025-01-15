package com.example.composecitylist.ui.screens.cities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composecitylist.domain.City
import com.example.composecitylist.ui.composables.CityRow
import com.example.composecitylist.ui.composables.FavouriteButton
import com.example.composecitylist.ui.composables.SearchBar
import com.example.composecitylist.ui.screens.LoadingScreen

@Composable
fun CitiesScreen(
    modifier: Modifier = Modifier,
    viewmodel: CityViewmodel,
    onCityClicked: (city: City) -> Unit) {
    val uiState = viewmodel.uiState.collectAsState()
    if (uiState.value.isLoading) {
        LoadingScreen()
    } else {
        Column(modifier
            .fillMaxSize()
            .padding(20.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                SearchBar(
                    Modifier
                        .weight(1f)
                        .height(55.dp)
                        .padding(end = 8.dp),
                    onTextChanged = viewmodel::filterCities
                )
                FavouriteButton(onClick = viewmodel::filterFavourites)
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(uiState.value.cities) { city ->
                    CityRow(
                        city,
                        onClick = { onCityClicked(city) },
                        onFavouriteClick = { viewmodel.updateFavouriteCity(city) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
