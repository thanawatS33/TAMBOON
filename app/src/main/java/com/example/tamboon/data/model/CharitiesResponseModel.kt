package com.example.tamboon.data.model

import com.google.gson.annotations.SerializedName

data class CharitiesResponseModel(
    @SerializedName("total") val total: Int,
    @SerializedName("data") val data: List<CharityModel>
)

data class CharityModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("logo_url") val logoUrl: String
)
