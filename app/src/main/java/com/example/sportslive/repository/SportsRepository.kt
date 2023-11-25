package com.example.sportslive.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportslive.api.LiveSportsService
import com.example.sportslive.model.SportsData


class SportsRepository(
    private val liveSportsService :LiveSportsService,
) {
    private val sportsLiveData = MutableLiveData<SportsData>()
    val sportsData : LiveData<SportsData>
        get() = sportsLiveData

    suspend fun getSportsData(){
        val result = liveSportsService.getSportsData()
        if(result?.body()!=null){
            sportsLiveData.postValue(result.body())
        }
    }
}