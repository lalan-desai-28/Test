package com.lalan.test.repository

import com.lalan.test.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("send-otp")
    fun login(@Field("contact_number") contactNumber: String): Call<LoginResponse>
}