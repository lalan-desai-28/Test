package com.lalan.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalan.test.model.UserProfileResponse
import com.lalan.test.repository.ProfileService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(private val profileService: ProfileService) :
    ViewModel() {

    private val _userProfileResult = MutableLiveData<Response<UserProfileResponse>>()
    val userProfileResult: LiveData<Response<UserProfileResponse>?> get() = _userProfileResult

    fun getFullProfile(token: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response =
                    profileService.getFullProfile(
                        "Bearer $token"
                    ).awaitResponse()
                _userProfileResult.postValue(response)
            }
        }
    }

}