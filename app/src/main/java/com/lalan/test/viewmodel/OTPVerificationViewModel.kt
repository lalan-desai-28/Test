package com.lalan.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalan.test.model.UserProfileResponse
import com.lalan.test.repository.OTPVerificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
class OTPVerificationViewModel @Inject constructor(private val otpVerificationService: OTPVerificationService) :
    ViewModel() {

    private val _otpVerificationResult = MutableLiveData<Response<UserProfileResponse>>()
    val otpVerificationResult: LiveData<Response<UserProfileResponse>> get() = _otpVerificationResult

    fun verifyOTP(contactNumber: String, otp: String) {
        viewModelScope.launch {
            val response =
                otpVerificationService
                    .verifyOTP(contactNumber, otp.toInt())
                    .awaitResponse()
            _otpVerificationResult.postValue(response)
        }
    }
}