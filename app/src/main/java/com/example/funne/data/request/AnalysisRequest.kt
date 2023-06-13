package com.example.funne.data.request

import com.google.gson.annotations.SerializedName

data class AnalysisRequest(
    @SerializedName("ingredients") val ingredients: String,
    @SerializedName("image_id") val imageId: String,
)
