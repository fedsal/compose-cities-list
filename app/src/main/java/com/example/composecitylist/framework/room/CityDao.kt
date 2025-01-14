package com.example.composecitylist.framework.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composecitylist.framework.room.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM cities")
    fun getAllCities(): Flow<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE is_favourite = 1")
    fun getFavouritesCities(): Flow<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities: List<CityEntity>)

    @Query("UPDATE cities SET is_favourite = :isFavourite WHERE id = :cityId")
    fun updateFavouriteCity(cityId: Int, isFavourite: Boolean)
}