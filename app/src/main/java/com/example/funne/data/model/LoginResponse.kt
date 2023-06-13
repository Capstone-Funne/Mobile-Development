package com.example.funne.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("picture")
    val picture: String? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("isLogin")
    var isLogin: Boolean = false,
) : Parcelable
