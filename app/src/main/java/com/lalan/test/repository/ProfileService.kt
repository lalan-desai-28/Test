package com.lalan.test.repository

import com.lalan.test.model.EditProfileResponse
import com.lalan.test.model.OTPVerificationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ProfileService {

    @FormUrlEncoded
    @POST("edit-profile")
    fun updateProfilePageOne(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("dob") dob: String,
    ): Call<OTPVerificationResponse>
}