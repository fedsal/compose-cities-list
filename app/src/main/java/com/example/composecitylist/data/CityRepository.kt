package com.example.composecitylist.data

import com.example.composecitylist.domain.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CityRepository(
    private val remoteDataSource: CityDataSource,
    private val localDataSource: CityDataSource
) {
    fun getCities(): Flow<List<City>> {
        return flow {
            val localCities = localDataSource.getCities()
            if (localCities.first().isEmpty()) {
                val remoteCities = remoteDataSource.getCities()
                localDataSource.updateCities(remoteCities.first().sortedBy { it.countryCode })
                emitAll(remoteCities)
            } else {
                emitAll(localCities.map { it.sortedBy { city -> city.countryCode } })
            }
        }
    }

    suspend fun updateFavouriteCity(cityId: Int, isFavourite: Boolean) {
        localDataSource.updateFavouriteCity(cityId, isFavourite)
    }
}