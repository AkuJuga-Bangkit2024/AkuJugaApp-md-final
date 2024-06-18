package com.example.akujuga.data.remote.response

import com.google.gson.annotations.SerializedName

data class AlphabetResponse(

	@field:SerializedName("images")
	val images: List<ImagesItem?>? = null,

	@field:SerializedName("camera_option")
	val cameraOption: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ImagesItem(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("letter")
	val letter: String? = null
)
