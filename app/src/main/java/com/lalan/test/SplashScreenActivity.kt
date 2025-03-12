package com.lalan.test

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.lalan.test.viewmodel.UserProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {


    private lateinit var userProfileViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val token = getSharedPreferences("session", MODE_PRIVATE).getString("token", "")

        if (token != null && token != "") {
            MyApplication.sessionToken = token
            userProfileViewModel = ViewModelProvider(this)[UserProfileViewModel::class]

            userProfileViewModel.getFullProfile(token)

            userProfileViewModel.userProfileResult.observe(this, { value ->
                if (value?.code() == 200) {
                    MyApplication.userProfile = value.body()
                    val mainDashboardIntent = Intent(this, MainDashboardActivity::class.java)
                    startActivity(mainDashboardIntent)
                } else {
                    val loginIntent = Intent(this, LoginActivity::class.java)
                    startActivity(loginIntent)
                }
                finish()
            })

        } else {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

    }
}