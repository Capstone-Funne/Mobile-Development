package com.example.funne.data.result

import com.example.funne.data.model.SuggestionResponse
import com.google.gson.annotations.SerializedName

data class SuggestionResult(
    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: List<SuggestionResponse>? = null,

    @field:SerializedName("message")
    val message: String? = null,
)
