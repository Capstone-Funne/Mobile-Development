package com.example.funne.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,
) : Parcelable
