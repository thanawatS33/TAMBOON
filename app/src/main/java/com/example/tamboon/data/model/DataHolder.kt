package com.example.tamboon.data.model

data class DataHolder<T>(val isSuccess: Boolean, val message: String?, val data: T?) {
    companion object {
        fun <T> success(data: T?): DataHolder<T> =
            DataHolder(true, null, data)
        fun <T> error(message: String?): DataHolder<T> =
            DataHolder(
                false,
                message,
                null
            )
    }
}