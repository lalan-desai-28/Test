package com.lalan.test

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lalan.test.adapter.ProfileSetupViewPagerAdapter
import com.lalan.test.fragments.ProfileSetupOne
import com.lalan.test.fragments.ProfileSetupThree
import com.lalan.test.fragments.ProfileSetupTwo
import com.lalan.test.model.Data
import com.lalan.test.viewmodel.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileSetupActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var previousTextview: TextView
    private lateinit var nextButton: Button
    private lateinit var progressBar: ProgressBar

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
        previousTextview = findViewById(R.id.previousTextview)
        nextButton = findViewById(R.id.nextButton)
        progressBar = findViewById(R.id.progressBar)

        editProfileViewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]
        viewPager.isUserInputEnabled = false

        val initialData = intent.extras?.getSerializable("data") as Data
        val token = MyApplication.sessionToken

        val ps1 = ProfileSetupOne(initialData, editProfileViewModel)
        val ps2 = ProfileSetupTwo(initialData, editProfileViewModel)
        val ps3 = ProfileSetupThree(initialData, editProfileViewModel)
        val fragmentsList = listOf(ps1, ps2, ps3)

        viewPager.adapter = ProfileSetupViewPagerAdapter(this, fragmentsList)

        TabLayoutMediator(tabLayout, viewPager, { _: TabLayout.Tab, _: Int -> }).attach()

        nextButton.setOnClickListener {
            if (viewPager.currentItem == 0) {
                progressBar.visibility = View.VISIBLE
                nextButton.visibility = View.GONE
                ps1.submitData(token)


                return@setOnClickListener
            }

        }


        editProfileViewModel.editProfileResult.observe(
            this
        ) { editProfileResponse ->
            if (editProfileResponse != null) {
                if (editProfileResponse.code() == 200)
                    viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                editProfileViewModel.editProfileResult.value = null
            }
        }



        editProfileViewModel.profilePageOneReqLoading.observe(this, { pOneReqLoading ->
            progressBar.visibility = if (pOneReqLoading) View.VISIBLE else View.GONE
            nextButton.visibility = if (pOneReqLoading) View.GONE else View.VISIBLE
        })


        previousTextview.setOnClickListener {
            viewPager.setCurrentItem(viewPager.currentItem - 1, true)
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 0) {
                    previousTextview.visibility = View.INVISIBLE
                }

                if (position == 1) {
                    nextButton.text = "Next"
                    previousTextview.visibility = View.VISIBLE
                }

                if (position == 2) {
                    nextButton.text = "Submit"
                }
            }
        })

    }


}

