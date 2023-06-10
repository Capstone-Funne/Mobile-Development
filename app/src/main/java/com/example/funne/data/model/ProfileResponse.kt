package com.example.funne.data.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("gender")
	val gender: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("picture")
	val picture: String
)
