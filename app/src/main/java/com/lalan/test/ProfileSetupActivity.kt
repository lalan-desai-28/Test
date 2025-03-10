package com.lalan.test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lalan.test.adapter.ProfileSetupViewPagerAdapter
import com.lalan.test.fragments.ProfileSetupOne
import com.lalan.test.fragments.ProfileSetupTwo
import com.lalan.test.model.Data
import com.lalan.test.viewmodel.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileSetupActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var editProfileViewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_setup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        editProfileViewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]

        val initialData = intent.extras?.getSerializable("data") as Data

        val ps1 = ProfileSetupOne(initialData, editProfileViewModel)

        val ps2 = ProfileSetupTwo(initialData, editProfileViewModel)

        val fragmentsList = listOf(ps1, ps2)

        viewPager.adapter = ProfileSetupViewPagerAdapter(this, fragmentsList)

        TabLayoutMediator(tabLayout, viewPager, { tab: TabLayout.Tab, index: Int ->
            tab.text = "Tab: ${index + 1}"
        }).attach()


        editProfileViewModel.viewPagerPosition.observe(this as LifecycleOwner, { position ->
            viewPager.setCurrentItem(position, true)
        })

    }
}