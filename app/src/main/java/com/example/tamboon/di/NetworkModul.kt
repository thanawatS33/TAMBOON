package com.example.tamboon.di

import com.example.tamboon.constant.ApiConstant
import com.example.tamboon.data.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

fun provideApi(gson: Gson) = Retrofit.Builder()
    .baseUrl(ApiConstant.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(ApiService::class.java)

fun provideGson() = GsonBuilder().create()



