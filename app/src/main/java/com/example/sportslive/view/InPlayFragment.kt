package com.example.sportslive.view
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportslive.R
import com.example.sportslive.adapter.CricketDataAdapter
import com.example.sportslive.adapter.SoccerDataAdapter
import com.example.sportslive.adapter.TennisDataAdapter
import com.example.sportslive.api.LiveSportsService
import com.example.sportslive.api.RetrofitHelper
import com.example.sportslive.databinding.FragmentInPlayBinding
import com.example.sportslive.model.CricketData
import com.example.sportslive.model.SoccerData
import com.example.sportslive.model.SportsData
import com.example.sportslive.model.TennisData
import com.example.sportslive.repository.SportsRepository
import com.example.sportslive.utils.AppConstants
import com.example.sportslive.utils.NetworkUtils
import com.example.sportslive.viewmodel.MainViewModel
import com.example.sportslive.viewmodel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class InPlayFragment : Fragment() {
    private lateinit var binding: FragmentInPlayBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var cricketDataAdapter : RecyclerView
    private lateinit var soccerDataAdapter : RecyclerView
    private lateinit var tennisDataAdapter : RecyclerView

    private var cricketDataList = mutableListOf<CricketData>()
    private var soccerDataList = mutableListOf<SoccerData>()
    private var tennisDataList = mutableListOf<TennisData>()

    private lateinit var liveSportsService : LiveSportsService
    private lateinit var sportRepository : SportsRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_in_play,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cricketDataAdapter = binding.rvCricket
        soccerDataAdapter = binding.rvSoccer
        tennisDataAdapter = binding.rvTennis

        //Checking Internet connection
        if (NetworkUtils.isInternetAvailable(requireActivity())) {
            //Api Calling
            liveSportsService = RetrofitHelper.getInstance().create(LiveSportsService::class.java)
            sportRepository = SportsRepository(liveSportsService)
        } else {
            //Showing Alert Dialog for no network
            val alertDialog = AlertDialog.Builder(requireActivity())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(AppConstants.NETWORK_ERROR)
                .setMessage(AppConstants.NETWORK_ERROR_MSG)
                .show()
        }

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(sportRepository)
        ).get(MainViewModel::class.java)
        mainViewModel.sportsData.observe(requireActivity(), Observer {
            setUpUI(it)
        })
    }

    private fun setUpUI(sportsData : SportsData){
        sportsData?.let {
            // Filtering Data and Storing Response in Data Class
            sportsData.data.forEach { e ->
                if(getDateCompareResult(e.openDate)>0){
                    var dateText = StringBuffer().append(AppConstants.DATA_TEXT).append(e.openDate)
                    when(e.sportId){
                        AppConstants.CRICKET_ID -> cricketDataList.add(CricketData(e.eventId.toInt(),e.eventName,dateText.toString()))
                        AppConstants.SOCCER_ID -> soccerDataList.add(SoccerData(e.eventId.toInt(),e.eventName,dateText.toString()))
                        AppConstants.TENNIS_ID -> tennisDataList.add(TennisData(e.eventId.toInt(),e.eventName,dateText.toString()))
                    }
                }
            }
            cricketDataAdapter.adapter = CricketDataAdapter(cricketDataList)
            cricketDataAdapter.layoutManager = LinearLayoutManager(activity)

            soccerDataAdapter.adapter = SoccerDataAdapter(soccerDataList)
            soccerDataAdapter.layoutManager = LinearLayoutManager(activity)

            tennisDataAdapter.adapter = TennisDataAdapter(tennisDataList)
            tennisDataAdapter.layoutManager = LinearLayoutManager(activity)
        }

    }

    //Comparing Dates
    private fun getDateCompareResult(openDate: String): Int {
        val currentTime: Date = Calendar.getInstance().getTime()
        val inputFormat = SimpleDateFormat(AppConstants.DATE_FORMAT)
        val eventData: Date? = inputFormat.parse(openDate)
        return currentTime.compareTo(eventData)
    }

}