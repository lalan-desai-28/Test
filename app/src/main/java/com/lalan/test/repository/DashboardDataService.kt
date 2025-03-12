package com.lalan.test.repository

import com.lalan.test.model.dashboard.DashboardDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface DashboardDataService {

    @GET("get-all-feeds")
    fun getDashboardData(@Header("Authorization") authToken: String): Call<DashboardDataResponse>

}