package com.example.composecitylist.framework.room

import com.example.composecitylist.data.CityDataSource
import com.example.composecitylist.domain.City
import com.example.composecitylist.framework.room.model.CityEntity
import com.example.composecitylist.framework.room.model.toDomainModel
import com.example.composecitylist.framework.room.model.toEntityModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalCityDataSource(
    private val cityDao: CityDao
): CityDataSource {
    override suspend fun getCities(): Flow<List<City>> = cityDao.getAllCities().map { it.map(CityEntity::toDomainModel) }

    override suspend fun updateFavouriteCity(cityId: Int, isFavourite: Boolean) = cityDao.updateFavouriteCity(cityId, isFavourite)

    override suspend fun updateCities(cities: List<City>) {
        cityDao.insertCities(cities.map(City::toEntityModel))
    }
}