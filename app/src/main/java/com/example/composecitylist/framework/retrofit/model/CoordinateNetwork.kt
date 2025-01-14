package com.example.composecitylist.framework.retrofit.model

import com.example.composecitylist.domain.Coordinate
import com.google.gson.annotations.SerializedName

data class CoordinateNetwork(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
)

fun CoordinateNetwork.toDomainModel(): Coordinate {
    return com.example.composecitylist.domain.Coordinate(
        latitude = latitude,
        longitude = longitude
    )
}
