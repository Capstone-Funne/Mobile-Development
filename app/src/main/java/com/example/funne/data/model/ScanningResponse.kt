package com.example.funne.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScanningResponse(

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("image_id")
    val imageId: String? = null,
) : Parcelable
