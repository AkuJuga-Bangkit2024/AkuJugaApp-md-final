package com.example.akujuga.data.remote.response

import com.google.gson.annotations.SerializedName

data class NumberResponse(

	@field:SerializedName("images")
	val images: List<NumberImagesItem?>? = null,

	@field:SerializedName("camera_option")
	val cameraOption: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class NumberImagesItem(

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null
)
