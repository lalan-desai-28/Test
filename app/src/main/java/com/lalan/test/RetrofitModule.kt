package com.lalan.test

import com.lalan.test.repository.LoginService
import com.lalan.test.repository.OTPVerificationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseURL(): String = "https://strengthen-numbers-stag.dev-imaginovation.net/api/v2/"

    @Provides
    @Singleton
    fun provideRetroFitObject(baseUrl: String): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideOTPVService(retrofit: Retrofit): OTPVerificationService =
        retrofit.create(OTPVerificationService::class.java)


}