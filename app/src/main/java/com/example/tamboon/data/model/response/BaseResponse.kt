package com.example.tamboon.data.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("error_code") val errorCode: String,
    @SerializedName("error_message") val errorMessage: String
)