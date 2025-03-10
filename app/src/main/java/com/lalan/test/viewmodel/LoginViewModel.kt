package com.lalan.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalan.test.model.LoginResponse
import com.lalan.test.repository.LoginService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginService: LoginService) : ViewModel() {

    private val _loginResult = MutableLiveData<Response<LoginResponse>>()
    val loginResult: LiveData<Response<LoginResponse>> get() = _loginResult

    fun performLogin(contactNumber: String) {
        viewModelScope.launch {
            val response = loginService.login(contactNumber).awaitResponse()
            _loginResult.postValue(response)
        }
    }
}