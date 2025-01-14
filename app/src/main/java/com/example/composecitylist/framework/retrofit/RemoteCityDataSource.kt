package com.example.composecitylist.framework.retrofit

import com.example.composecitylist.data.CityDataSource
import com.example.composecitylist.domain.City
import com.example.composecitylist.framework.retrofit.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCityDataSource: CityDataSource {
    override suspend fun getCities(): Flow<List<City>> {
        return flow {
            val cities = CityClient.service.getCities().map { it.toDomainModel() }
            emit(cities)
        }
    }

    override suspend fun updateFavouriteCity(cityId: Int, isFavourite: Boolean) {
        throw UnsupportedOperationException("Not supported by remote data source")
    }

    override suspend fun updateCities(cities: List<City>) {
        throw UnsupportedOperationException("Not supported by remote data source")
    }
}