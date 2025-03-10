package com.lalan.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalan.test.model.EditProfileResponse
import com.lalan.test.model.OTPVerificationResponse
import com.lalan.test.repository.ProfileService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(private val profileService: ProfileService) :
    ViewModel() {

    private val _editProfileResult = MutableLiveData<Response<OTPVerificationResponse>>()
    val editProfileResult: LiveData<Response<OTPVerificationResponse>> get() = _editProfileResult

    val viewPagerPosition = MutableLiveData<Int>().apply { value = 0 }

    fun editProfileScreenOne(name: String, email: String, dob: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response =
                    profileService.updateProfilePageOne(name, email, dob).awaitResponse()
                _editProfileResult.postValue(response)
            }
        }
    }

}