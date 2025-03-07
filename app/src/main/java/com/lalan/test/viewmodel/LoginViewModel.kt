package com.lalan.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalan.test.MyApplication
import com.lalan.test.model.LoginResponse
import com.lalan.test.repository.LoginService
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Response<LoginResponse>>()
    val loginResult: LiveData<Response<LoginResponse>> get() = _loginResult

    fun performLogin(contactNumber: String) {
        viewModelScope.launch {
            val response =
                MyApplication.retroFitObject.create(LoginService::class.java).login(contactNumber)
                    .awaitResponse()
            _loginResult.postValue(response)
        }
    }
}