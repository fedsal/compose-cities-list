package com.example.composecitylist.data

import com.example.composecitylist.domain.City
import kotlinx.coroutines.flow.Flow

interface CityDataSource {
    suspend fun getCities(): Flow<List<City>>
    suspend fun updateFavouriteCity(cityId: Int, isFavourite: Boolean)
    suspend fun updateCities(cities: List<City>)
}