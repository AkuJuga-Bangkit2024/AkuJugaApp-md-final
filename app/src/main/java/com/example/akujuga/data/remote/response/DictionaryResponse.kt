package com.example.akujuga.data.remote.response

import com.google.gson.annotations.SerializedName

data class DictionaryResponse(

	@field:SerializedName("words")
	val words: List<WordsItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class WordsItem(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("word")
	val word: String? = null
)
