package com.example.tamboon.data.repository

import com.example.tamboon.constant.Constant
import com.example.tamboon.data.api.ApiService
import com.example.tamboon.data.model.response.CharitiesResponseModel
import com.example.tamboon.data.model.ResultResponse
import java.lang.Exception

interface TamBoonRepository {

    suspend fun getCharities(): ResultResponse<CharitiesResponseModel>

}

class TamBoonRepositoryImpl(private val api: ApiService) : TamBoonRepository {

    override suspend fun getCharities(): ResultResponse<CharitiesResponseModel> {
        return try {
            ResultResponse.success(api.getCharitiesService())
        } catch (e: Exception) {
            ResultResponse.error(Constant.TEXT_ERROR)
        }
    }

}