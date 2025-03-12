package com.lalan.test.model.dashboard

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("feed_id"       ) var feedId       : Int    = 0,
  @SerializedName("user_id"       ) var userId       : Int?    = null,
  @SerializedName("name"          ) var name         : String? = null,
  @SerializedName("username"      ) var username     : String? = null,
  @SerializedName("profile_photo" ) var profilePhoto : String? = null,
  @SerializedName("type"          ) var type         : String? = null,
  @SerializedName("created_at"    ) var createdAt    : String? = null,
  @SerializedName("is_active"     ) var isActive     : Int?    = null,
  @SerializedName("is_published"  ) var isPublished  : Int?    = null,
  @SerializedName("is_following"  ) var isFollowing  : Int?    = null,
  @SerializedName("is_blocked"    ) var isBlocked    : Int?    = null,
  @SerializedName("post"          ) var post         : Post?   = Post()

)