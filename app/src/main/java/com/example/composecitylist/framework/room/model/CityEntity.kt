package com.example.composecitylist.framework.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composecitylist.domain.City

@Entity(tableName = "cities")
data class CityEntity (
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "country_code")
    val countryCode: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean = false
)

fun CityEntity.toDomainModel() = City(
    id = id,
    countryCode = countryCode,
    name = name,
    latitude = latitude,
    longitude = longitude,
    isFavourite = isFavourite,
)

fun City.toEntityModel() = CityEntity(
    id = id,
    countryCode = countryCode,
    name = name,
    latitude = latitude,
    longitude = longitude,
    isFavourite = isFavourite,
)