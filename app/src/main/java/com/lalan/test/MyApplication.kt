package com.lalan.test

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    companion object {
        val retroFitObject: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://strengthen-numbers.dev-imaginovation.net/api/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}