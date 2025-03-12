package com.lalan.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.lalan.test.model.UserProfileResponse
import com.lalan.test.viewmodel.OTPVerificationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPVerificationActivity : AppCompatActivity() {

    private lateinit var resendOTPTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var otpEditText: TextView
    private lateinit var mobileNumberDescTextView: TextView
    private lateinit var verifyButton: Button
    private lateinit var otpVerificationViewModel: OTPVerificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otpverification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resendOTPTextView = findViewById(R.id.resendOTPTextView)
        timerTextView = findViewById(R.id.timerTextView)
        otpEditText = findViewById(R.id.otpEditText)
        verifyButton = findViewById(R.id.verifyButton)
        mobileNumberDescTextView = findViewById(R.id.mobileNumberDescTextView)


        otpVerificationViewModel = ViewModelProvider(this)[OTPVerificationViewModel::class]

        val contactNumber = intent.extras?.getString("contactNumber")

        mobileNumberDescTextView.text =
            "We have sent the verification code to your $contactNumber mobile number."


        verifyButton.setOnClickListener {
            if (otpEditText.text.length < 4) {
                Toast.makeText(this, "OTP can not be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            otpVerificationViewModel.verifyOTP(
                "+1${contactNumber.toString()}",
                otpEditText.text.toString()
            )
        }

        otpVerificationViewModel.otpVerificationResult.observe(this) { otpVResponse ->
            if (otpVResponse.code() == 200) {

                val mainDashboardIntent = Intent(this, MainDashboardActivity::class.java)

                val sp = getSharedPreferences("session", MODE_PRIVATE)
                sp.edit().putString("token", otpVResponse.headers().get("X-Authorization-Token"))
                    .apply()

                MyApplication.sessionToken =
                    otpVResponse.headers().get("X-Authorization-Token") ?: ""
                MyApplication.userProfile = otpVResponse.body()

                startActivity(mainDashboardIntent)
                finish()
            } else {
                val gson = Gson()
                val message = gson.fromJson(
                    otpVResponse.errorBody()!!.charStream(),
                    UserProfileResponse::class.java
                )
                Toast.makeText(
                    this,
                    message.meta.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }


}