package com.example.composecitylist.framework.retrofit

import com.example.composecitylist.data.CityDataSource
import com.example.composecitylist.domain.City
import com.example.composecitylist.framework.retrofit.model.toDomainModel

class RemoteCityDataSource: CityDataSource {
    override suspend fun getCities(): List<City> {
        return CityClient.service.getCities().map { it.toDomainModel() }
    }

    override suspend fun getFavouritesCities(): List<City> {
        throw UnsupportedOperationException("Not supported by remote data source")
    }
}