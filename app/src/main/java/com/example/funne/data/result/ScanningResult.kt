package com.example.funne.data.result

import com.example.funne.data.model.ScanningResponse
import com.google.gson.annotations.SerializedName

data class ScanningResult(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val scanningResponse: ScanningResponse? = null,

    @field:SerializedName("message")
    val message: String? = null
)