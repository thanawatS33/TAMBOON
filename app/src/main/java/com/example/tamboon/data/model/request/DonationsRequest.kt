package com.example.tamboon.data.model.request

import com.google.gson.annotations.SerializedName

data class DonationsRequest(
    @SerializedName("name") val name: String,
    @SerializedName("token") val token: String,
    @SerializedName("amount") val amount: Int
)