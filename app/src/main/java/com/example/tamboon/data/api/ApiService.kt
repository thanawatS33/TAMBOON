package com.example.tamboon.data.api

import com.example.tamboon.constant.ApiConstant
import com.example.tamboon.data.model.CharitiesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiConstant.API_CHARITIES)
    suspend fun getCharitiesService(): CharitiesResponseModel

}