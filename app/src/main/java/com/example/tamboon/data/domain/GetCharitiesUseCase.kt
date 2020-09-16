package com.example.tamboon.data.domain

import com.example.tamboon.data.model.CharitiesResponseModel
import com.example.tamboon.data.repository.TamBoonRepository
import com.example.tamboon.util.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharitiesUseCase(private val repository: TamBoonRepository) {

    suspend operator fun invoke(): ResultResponse<CharitiesResponseModel> {
        return withContext(Dispatchers.IO) {
            return@withContext repository.getCharities()
        }
    }

}