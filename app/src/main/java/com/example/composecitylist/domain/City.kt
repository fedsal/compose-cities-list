package com.example.composecitylist.domain

import com.google.gson.annotations.SerializedName

data class City (
    @SerializedName("_id")
    val id: Int,
    @SerializedName("country")
    val countryCode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coordinate: Coordinate
)
