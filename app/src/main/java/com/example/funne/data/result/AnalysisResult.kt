package com.example.funne.data.result

import com.example.funne.data.model.AnalysisResponse
import com.google.gson.annotations.SerializedName

data class AnalysisResult(
    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: List<AnalysisResponse>? = null,

    @field:SerializedName("message")
    val message: String? = null
)
