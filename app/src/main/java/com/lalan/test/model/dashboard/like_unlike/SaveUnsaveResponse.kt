package com.lalan.test.model.dashboard.like_unlike

import com.google.gson.annotations.SerializedName

data class LikeUnlikeResponse(
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("meta") var meta: Meta? = Meta()
)