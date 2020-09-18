package com.example.tamboon.data.domain

import com.example.tamboon.data.model.DataHolder
import com.example.tamboon.data.model.request.DonationsRequest
import com.example.tamboon.data.model.response.BaseResponse
import com.example.tamboon.data.repository.TamBoonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DonationsUseCase constructor(private val repository: TamBoonRepository) {

    suspend operator fun invoke(request: DonationsRequest): DataHolder<BaseResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext repository.getDonation(request)
        }
    }

}