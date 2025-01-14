package com.example.composecitylist.framework.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CityClient {

    private const val BASE_URL = "https://gist.githubusercontent.com/hernan-uala/"

    private val retrofitInstance by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: CityService by lazy {
        retrofitInstance.create(CityService::class.java)
    }
}