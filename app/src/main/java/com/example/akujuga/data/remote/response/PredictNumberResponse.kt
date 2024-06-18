package com.example.akujuga.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictNumberResponse(

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
