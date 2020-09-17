package com.example.tamboon.data.api

import com.example.tamboon.constant.ApiConstant
import com.example.tamboon.data.model.response.CharitiesResponseModel
import retrofit2.http.GET

interface ApiService {

    @GET(ApiConstant.API_CHARITIES)
    suspend fun getCharitiesService(): CharitiesResponseModel

}