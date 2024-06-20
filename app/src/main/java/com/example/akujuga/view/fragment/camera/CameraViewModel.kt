package com.example.akujuga.view.fragment.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akujuga.data.UserRepository
import com.example.akujuga.data.remote.response.PredictAlphabetResponse
import com.example.akujuga.data.remote.response.PredictNumberResponse
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel(private val repository: UserRepository): ViewModel() {

//    private val _predictingImageNumber = MutableLiveData<PredictNumberResponse>()
//    val predictingImageNumber: LiveData<PredictNumberResponse> = _predictingImageNumber
//
//    private val _predictingImageAlphabet = MutableLiveData<PredictAlphabetResponse>()
//    val predictingImageAlphabet: LiveData<PredictAlphabetResponse> = _predictingImageAlphabet
//
//
//    fun classifyImageAlphabet(imageFile: File) {
//        viewModelScope.launch {
//            val response = repository.classifyImageAlphabet(imageFile)
//            response?.let {
//                _predictingImageAlphabet.value = it
//            }
//        }
//    }
//
//    fun classifyImageNumber(imageFile: File) {
//        viewModelScope.launch {
//            val response = repository.classifyImageNumber(imageFile)
//            response?.let {
//                _predictingImageNumber.value = it
//            }
//        }
//    }
}