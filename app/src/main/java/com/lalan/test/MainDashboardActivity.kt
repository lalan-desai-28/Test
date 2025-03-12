package com.lalan.test

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.lalan.test.adapter.DashboardMenuAdapter
import com.lalan.test.fragments.HomeScreenFragment
import com.lalan.test.viewmodel.DashboardDataViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainDashboardActivity : AppCompatActivity() {

    private lateinit var mainViewPager: ViewPager2
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainViewPager = findViewById(R.id.mainViewPager)
        bottomNavigation = findViewById(R.id.bottomNavigation)


        val userData = MyApplication.userProfile

        if (userData == null) {
            // go back to login activity
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        } else {
            val dashboardDataViewModel = ViewModelProvider(this)[DashboardDataViewModel::class]
            val fList = listOf(HomeScreenFragment(dashboardDataViewModel))
            mainViewPager.adapter = DashboardMenuAdapter(this, fList)
        }


        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> mainViewPager.setCurrentItem(0, true)
            }
            true
        }
    }
}