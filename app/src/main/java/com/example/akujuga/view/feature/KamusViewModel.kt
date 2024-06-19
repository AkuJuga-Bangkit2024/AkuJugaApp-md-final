package com.example.akujuga.view.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akujuga.data.UserRepository
import com.example.akujuga.data.remote.response.DictionaryResponse
import com.example.akujuga.data.remote.response.NumberResponse
import kotlinx.coroutines.launch

class KamusViewModel(private val repository: UserRepository): ViewModel() {

    private val _dictionaryResponse = MutableLiveData<DictionaryResponse>()
    val dictionaryResponse: LiveData<DictionaryResponse> = _dictionaryResponse

    fun getDictionary() {
        viewModelScope.launch {
            val response = repository.getDictionary()
            response?.let {
                _dictionaryResponse.value = it
            }
        }
    }
}