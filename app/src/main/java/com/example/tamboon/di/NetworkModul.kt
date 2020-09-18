package com.example.tamboon.di

import com.example.tamboon.constant.ApiConstant
import com.example.tamboon.data.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

val apiInterceptor = object : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url = request.url.toString()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            val jsonObject = JSONObject()
            jsonObject.put("success", response.isSuccessful)
            jsonObject.put("error_message", response.message)
            return response.newBuilder().body(jsonObject.toString().toResponseBody()).build()
        }
        return response
    }
}

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .addInterceptor(apiInterceptor)
    .build()

fun provideApi(gson: Gson) = Retrofit.Builder()
    .baseUrl(ApiConstant.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(ApiService::class.java)

fun provideGson() = GsonBuilder().create()



