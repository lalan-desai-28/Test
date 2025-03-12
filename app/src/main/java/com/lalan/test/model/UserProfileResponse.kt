package com.lalan.test.model

import java.io.Serializable

data class UserProfileResponse(val data: Data, val meta: Meta)

data class Data(
    val id: Int,
    val name: String,
    val email: String,
    val contact_number: String,
    val dob: String,
    val bio: String,
    val gender: String,
    val latitude: Float,
    val longitude: Float,
    val fitness_level: String,
    val interests: Array<String>,
    val profile_photo: String,
    val registered_at: String,
    val total_followers: Int,
    val total_followings: Int,
    val is_following: Int,
    val is_blocked: Int,
    val share_locatio: Int
) : Serializable
