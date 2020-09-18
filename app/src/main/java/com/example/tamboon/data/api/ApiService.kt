package com.example.tamboon.data.api

import com.example.tamboon.constant.ApiConstant
import com.example.tamboon.data.model.request.DonationsRequest
import com.example.tamboon.data.model.response.BaseResponse
import com.example.tamboon.data.model.response.CharitiesResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET(ApiConstant.API_CHARITIES)
    suspend fun getCharitiesService(): CharitiesResponseModel

    @POST(ApiConstant.API_DONATIONS)
    suspend fun getDonation(@Body request: DonationsRequest): BaseResponse
}