package com.example.composecitylist.data

import com.example.composecitylist.domain.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onCompletion

class CityRepository(
    private val remoteDataSource: CityDataSource,
    private val localDataSource: CityDataSource
) {
    suspend fun getCities(): Flow<List<City>> {
        return localDataSource.getCities().onCompletion {
            val remoteCities = remoteDataSource.getCities().first()
            localDataSource.updateCities(remoteCities)
        }
    }

    suspend fun updateFavouriteCity(cityId: Int, isFavourite: Boolean) {
        localDataSource.updateFavouriteCity(cityId, isFavourite)
    }
}