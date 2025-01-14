package com.example.composecitylist.domain

data class City (
    val id: Int,
    val countryCode: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val isFavourite: Boolean = false,
)
