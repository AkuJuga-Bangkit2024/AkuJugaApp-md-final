package com.example.akujuga.data.remote.retrofit

import com.example.akujuga.data.remote.response.AlphabetResponse
import com.example.akujuga.data.remote.response.DictionaryResponse
import com.example.akujuga.data.remote.response.NumberResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService{

    @GET("learn/alphabets")
    suspend fun getAlphabets(): AlphabetResponse

    @GET("/learn/numbers")
    suspend fun getNumbers(): NumberResponse

    @GET("/learn/dictionary")
    suspend fun getDictionary(): DictionaryResponse
}