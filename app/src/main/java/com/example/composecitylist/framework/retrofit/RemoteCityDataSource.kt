package com.example.composecitylist.framework.retrofit

import com.example.composecitylist.data.CityDataSource
import com.example.composecitylist.domain.City

class RemoteCityDataSource: CityDataSource {
    override suspend fun getCities(): List<City> {
        return CityClient.service.getCities()
    }

    override suspend fun getFavouritesCities(): List<City> {
        throw UnsupportedOperationException("Not supported by remote data source")
    }
}