package com.example.akujuga.view.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akujuga.data.UserRepository
import com.example.akujuga.data.remote.response.NumberResponse
import com.example.akujuga.data.remote.response.PredictAlphabetResponse
import com.example.akujuga.data.remote.response.PredictNumberResponse
import kotlinx.coroutines.launch
import java.io.File

class NumberViewModel(private val repository: UserRepository): ViewModel() {

    private val _numberResponse = MutableLiveData<NumberResponse>()
    val numberResponse: LiveData<NumberResponse> = _numberResponse

    fun getNumber() {
        viewModelScope.launch {
            val response = repository.getNumber()
            response?.let {
                _numberResponse.value = it
            }
        }
    }
}