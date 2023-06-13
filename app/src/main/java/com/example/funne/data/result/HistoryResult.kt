package com.example.funne.data.result

import com.example.funne.data.model.HistoryResponse
import com.google.gson.annotations.SerializedName

data class HistoryResult(
    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: List<HistoryResponse>? = null,

    @field:SerializedName("message")
    val message: String? = null,
)
