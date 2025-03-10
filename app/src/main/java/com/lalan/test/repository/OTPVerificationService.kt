package com.lalan.test.repository

import com.lalan.test.model.OTPVerificationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OTPVerificationService {
    @FormUrlEncoded
    @POST("verify-otp")
    fun verifyOTP(
        @Field("contact_number") contactNumber: String, @Field("otp") otp: Int
    ): Call<OTPVerificationResponse>
}