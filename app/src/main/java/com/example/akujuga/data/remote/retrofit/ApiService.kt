package com.example.akujuga.data.remote.retrofit

import com.example.akujuga.data.remote.response.AlphabetResponse
import com.example.akujuga.data.remote.response.DictionaryResponse
import com.example.akujuga.data.remote.response.NumberResponse
import com.example.akujuga.data.remote.response.PredictAlphabetResponse
import com.example.akujuga.data.remote.response.PredictNumberResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService{

    @GET("learn/alphabets")
    suspend fun getAlphabets(): AlphabetResponse

    @GET("/learn/numbers")
    suspend fun getNumbers(): NumberResponse

    @GET("/learn/dictionary")
    suspend fun getDictionary(): DictionaryResponse

    @Multipart
    @POST("/classifyImageAlphabet")
    suspend fun classifyImageAlphabet(
        @Part file: MultipartBody.Part
    ): PredictAlphabetResponse

    @Multipart
    @POST("/classifyImageAlphabet")
    suspend fun classifyImageNumber(
        @Part file: MultipartBody.Part
    ): PredictNumberResponse
}