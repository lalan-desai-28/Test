package com.lalan.test.repository

import com.lalan.test.model.dashboard.like_unlike.LikeUnlikeResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface LikeUnlikeService {
    @FormUrlEncoded
    @POST("like-unlike-feed")
    fun likeUnlikeFeed(
        @Field("feed_id") feedId: Int,
        @Header("Authorization") authToken: String
    ): Call<LikeUnlikeResponse>
}