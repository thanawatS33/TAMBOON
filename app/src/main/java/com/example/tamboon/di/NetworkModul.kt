package com.example.tamboon.di

import com.example.tamboon.constant.ApiConstant
import com.example.tamboon.data.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun provideApi(gson: Gson) = Retrofit.Builder()
    .baseUrl(ApiConstant.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(ApiService::class.java)

fun provideGson() = GsonBuilder().create()