package com.example.composecitylist.framework.retrofit.model

import com.google.gson.annotations.SerializedName

data class CoordinateNetwork(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
)
