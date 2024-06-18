package com.example.akujuga.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictAlphabetResponse(

	@field:SerializedName("letter")
	val letter: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
