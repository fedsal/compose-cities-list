package com.example.composecitylist.data

class CityRepository(
    private val remoteDataSource: CityDataSource,
    private val localDataSource: CityDataSource
) {
    suspend fun getCountries() = remoteDataSource.getCities()
    suspend fun getFavouritesCountries() = localDataSource.getFavouritesCities()
}