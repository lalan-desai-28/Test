package com.lalan.test.model.dashboard

import com.google.gson.annotations.SerializedName
import com.lalan.test.model.dashboard.Media


data class Post (

    @SerializedName("post_id"        ) var postId        : Int?             = null,
    @SerializedName("title"          ) var title         : String?          = null,
    @SerializedName("description"    ) var description   : String?          = null,
    @SerializedName("media"          ) var media         : ArrayList<Media> = arrayListOf(),
    @SerializedName("is_liked"       ) var isLiked       : Int?             = 0,
    @SerializedName("likes_count"    ) var likesCount    : Int?             = 0,
    @SerializedName("is_saved"       ) var isSaved       : Int?             = 0,
    @SerializedName("saved_count"    ) var savedCount    : Int?             = 0,
    @SerializedName("comments_count" ) var commentsCount : Int?             = 0

)