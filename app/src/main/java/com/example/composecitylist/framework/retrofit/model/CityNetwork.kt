package com.example.composecitylist.framework.retrofit.model

import com.example.composecitylist.domain.City
import com.google.gson.annotations.SerializedName

data class CityNetwork (
    @SerializedName("_id")
    val id: Int,
    @SerializedName("country")
    val countryCode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coordinate: CoordinateNetwork
)

fun CityNetwork.toDomainModel(): City {
    return City(
        id = id,
        countryCode = countryCode,
        name = name,
        latitude = coordinate.latitude,
        longitude = coordinate.longitude,
    )
}
