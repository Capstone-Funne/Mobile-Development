package com.example.funne.data.result

import com.example.funne.data.model.LoginResponse
import com.google.gson.annotations.SerializedName

data class GeneralResult(

    @field:SerializedName("status_code")
    val statusCode: Int?,

    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("data")
    val loginResponse: LoginResponse?,
)
