package com.example.composecitylist.data

import com.example.composecitylist.domain.City

interface CityDataSource {
    suspend fun getCities(): List<City>
    suspend fun getFavouritesCities(): List<City>
}