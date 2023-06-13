package com.example.funne.data.result

import com.example.funne.data.model.ProductResponse
import com.google.gson.annotations.SerializedName

data class ProductResult (
    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: List<ProductResponse>? = null,

    @field:SerializedName("message")
    val message: String? = null
)