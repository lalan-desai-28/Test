package com.lalan.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalan.test.MyApplication
import com.lalan.test.model.OTPVerificationResponse
import com.lalan.test.repository.OTPVerificationService
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse

class OTPVerificationViewModel : ViewModel() {

    private val _otpVerificationResult = MutableLiveData<Response<OTPVerificationResponse>>()
    val otpVerificationResult: LiveData<Response<OTPVerificationResponse>> get() = _otpVerificationResult

    fun verifyOTP(contactNumber: String, otp: String) {
        viewModelScope.launch {
            val response =
                MyApplication.retroFitObject.create(OTPVerificationService::class.java)
                    .verifyOTP(contactNumber, otp.toInt())
                    .awaitResponse()
            _otpVerificationResult.postValue(response)
        }
    }
}