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
class EditProfileViewModel @Inject constructor(private val profileService: ProfileService) :
    ViewModel() {

    private val _editProfileResult = MutableLiveData<Response<UserProfileResponse>>()
    val editProfileResult: LiveData<Response<UserProfileResponse>?> get() = _editProfileResult


    private val _profilePageOneReqLoading = MutableLiveData<Boolean>()
    val profilePageOneReqLoading: LiveData<Boolean> get() = _profilePageOneReqLoading

    fun editProfileScreenOne(name: String, email: String, dob: String, token: String) {
        editProfileResult.removeObserver { }
        viewModelScope.launch {
            _profilePageOneReqLoading.postValue(true)
            withContext(Dispatchers.IO) {
                val response =
                    profileService.updateProfilePageOne(
                        name,
                        email,
                        dob,
                        "Bearer $token"
                    ).awaitResponse()
                _editProfileResult.postValue(response)
                _profilePageOneReqLoading.postValue(false)

            }
        }
    }

}