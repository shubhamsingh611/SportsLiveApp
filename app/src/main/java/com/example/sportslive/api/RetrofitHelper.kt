package com.example.sportslive.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    //Retrofit Implementation for API Calling
    private const val BASE_URL = "https://centre.7wickets.net:4000/api/v1-custom/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}