package com.lalan.test.model.dashboard.like_unlike

import com.google.gson.annotations.SerializedName

data class Data (
  @SerializedName("feed_id"     ) var feedId     : Int? = null,
  @SerializedName("is_liked"    ) var isLiked    : Int? = null,
  @SerializedName("likes_count" ) var likesCount : Int? = null

)