package com.lalan.test

import android.app.Application
import com.lalan.test.model.UserProfileResponse
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        var sessionToken: String = ""
        var userProfile: UserProfileResponse? = null
    }
}