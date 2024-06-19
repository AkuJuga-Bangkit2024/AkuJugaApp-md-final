package com.example.akujuga.view.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akujuga.data.UserRepository
import com.example.akujuga.data.remote.response.AlphabetResponse
import com.example.akujuga.data.remote.response.NumberResponse
import com.example.akujuga.data.remote.response.PredictAlphabetResponse
import kotlinx.coroutines.launch
import java.io.File

class AlphabetViewModel(private val repository: UserRepository) : ViewModel() {

    private val _alphaberResponse = MutableLiveData<AlphabetResponse>()
    val alphabetResponse: LiveData<AlphabetResponse> = _alphaberResponse


    fun getAlphabet() {
        viewModelScope.launch {
            val response = repository.getAlphabet()
            response?.let {
                _alphaberResponse.value = it
            }
        }
    }


}