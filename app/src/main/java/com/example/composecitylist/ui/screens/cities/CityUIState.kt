package com.example.composecitylist.ui.screens.cities

import com.example.composecitylist.domain.City

data class CityUIState (
    val cities: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
