package com.example.sportslive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.sportslive.adapter.ViewPagerAdapter
import com.example.sportslive.databinding.ActivityMainBinding
import com.example.sportslive.utils.AppConstants
import com.example.sportslive.view.InPlayFragment
import com.example.sportslive.view.TodayFragment
import com.example.sportslive.view.TomorrowFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewPager : ViewPager
    private lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //View Pager Implementation
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager = mainBinding.viewPager
        tabLayout = mainBinding.tabLayout
        viewPagerAdapter.addFragment(InPlayFragment(), AppConstants.IN_PLAY_TEXT)
        viewPagerAdapter.addFragment(TodayFragment(),AppConstants.TODAY_TEXT)
        viewPagerAdapter.addFragment(TomorrowFragment(),AppConstants.TOMORROW_TEXT)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}