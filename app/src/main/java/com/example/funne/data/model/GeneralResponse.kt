package com.example.funne.data.model

import com.google.gson.annotations.SerializedName

data class GeneralResponse(

    @field:SerializedName("status_code")
    val statusCode: Int?,

    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("data")
    val loginResponse: LoginResponse?,
)
