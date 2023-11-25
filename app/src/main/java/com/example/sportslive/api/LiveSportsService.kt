package com.example.sportslive.api

import com.example.sportslive.model.SportsData
import retrofit2.Response
import retrofit2.http.GET

interface LiveSportsService {
    //Passing API key to get response
    @GET("getLiveMatches")
    suspend fun getSportsData() : Response<SportsData>
}