package com.example.tamboon.data.model

data class ResultResponse<T>(val isSuccess: Boolean, val message: String?, val data: T?) {
    companion object {
        fun <T> success(data: T?): ResultResponse<T> =
            ResultResponse(true, null, data)
        fun <T> error(message: String?): ResultResponse<T> =
            ResultResponse(
                false,
                message,
                null
            )
    }
}