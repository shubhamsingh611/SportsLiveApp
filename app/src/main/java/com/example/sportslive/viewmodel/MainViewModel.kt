package com.example.sportslive.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportslive.model.SportsData
import com.example.sportslive.repository.SportsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val sportsRepository : SportsRepository) : ViewModel(){
    init {
        viewModelScope.launch(Dispatchers.IO){
            sportsRepository.getSportsData()
        }
    }
    val sportsData : LiveData<SportsData>
        get() = sportsRepository.sportsData
}