package com.lalan.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.lalan.test.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var continueButton: Button
    private lateinit var mobileNumberEditText: EditText
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        continueButton = findViewById(R.id.continueButton)
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginViewModel.loginResult.observe(this, { response ->
            if (response.code() == 200) {
                val otpVerificationIntent = Intent(this, OTPVerificationActivity::class.java)
                otpVerificationIntent.putExtra("contactNumber", mobileNumberEditText.text.toString())
                startActivity(otpVerificationIntent)
            } else {
                Toast.makeText(this, "Error: ${response.body()?.meta}", Toast.LENGTH_LONG).show()
            }
        })


        // REMOVE THIS!!!!
        loginViewModel.performLogin("+1" + mobileNumberEditText.text.toString())

        continueButton.setOnClickListener {


            if (mobileNumberEditText.text.length != 10) {
                Toast.makeText(this, "Mobile number length should be 10.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            loginViewModel.performLogin("+1" + mobileNumberEditText.text.toString())


        }
    }
}