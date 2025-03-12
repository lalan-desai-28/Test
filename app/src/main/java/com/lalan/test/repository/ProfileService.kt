package com.lalan.test.repository

import com.lalan.test.model.UserProfileResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface ProfileService {

    @POST("edit-profile")
    fun getFullProfile(
        @Header("Authorization") authToken: String,
    ): Call<UserProfileResponse>

    @FormUrlEncoded
    @POST("edit-profile")
    fun updateProfilePageOne(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("dob") dob: String,
        @Header("Authorization") string: String,
    ): Call<UserProfileResponse>
}