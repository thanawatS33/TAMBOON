package com.example.tamboon.data.repository

import com.example.tamboon.BuildConfig
import com.example.tamboon.constant.Constant
import com.example.tamboon.data.api.ApiService
import com.example.tamboon.data.model.response.CharitiesResponseModel
import com.example.tamboon.data.model.DataHolder
import java.lang.Exception

interface TamBoonRepository {

    suspend fun getCharities(): DataHolder<CharitiesResponseModel>

    fun getOmisePublicKey(): String

}

class TamBoonRepositoryImpl(private val api: ApiService) : TamBoonRepository {

    override suspend fun getCharities(): DataHolder<CharitiesResponseModel> {
        return try {
            DataHolder.success(api.getCharitiesService())
        } catch (e: Exception) {
            DataHolder.error(Constant.TEXT_ERROR)
        }
    }

    override fun getOmisePublicKey() = BuildConfig.OMISE_PKEY

}