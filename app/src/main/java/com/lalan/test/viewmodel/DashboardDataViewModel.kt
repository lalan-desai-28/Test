package com.lalan.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalan.test.model.dashboard.DashboardDataResponse
import com.lalan.test.model.dashboard.like_unlike.LikeUnlikeResponse
import com.lalan.test.repository.DashboardDataService
import com.lalan.test.repository.LikeUnlikeService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
class DashboardDataViewModel @Inject constructor(
    private val dashboardDataService: DashboardDataService,
    private val likeUnlikeService: LikeUnlikeService
) :
    ViewModel() {

    private val _dashboardDataResult = MutableLiveData<Response<DashboardDataResponse>>()
    val dashboardDataResult: LiveData<Response<DashboardDataResponse>?> get() = _dashboardDataResult

    private val _likeUnlikeResult = MutableLiveData<Response<LikeUnlikeResponse>>()
    val likeUnlikeResult: LiveData<Response<LikeUnlikeResponse>?> get() = _likeUnlikeResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isLikeUnlikeLoading = MutableLiveData<Boolean>()
    val isLikeUnlikeLoading: LiveData<Boolean> get() = _isLikeUnlikeLoading


    fun getDashboardData(token: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            withContext(Dispatchers.IO) {
                val response =
                    dashboardDataService.getDashboardData(
                        "Bearer $token"
                    ).awaitResponse()
                _dashboardDataResult.postValue(response)
                _isLoading.postValue(false)
            }
        }
    }

    fun likeUnlikeFeed(feedId: Int, token: String) {
        viewModelScope.launch {
            _isLikeUnlikeLoading.postValue(true)
            withContext(Dispatchers.IO) {
                val response =
                    likeUnlikeService.likeUnlikeFeed(
                        feedId,
                        "Bearer $token"
                    ).awaitResponse()
                _likeUnlikeResult.postValue(response)
                _isLikeUnlikeLoading.postValue(false)
            }
        }
    }

}