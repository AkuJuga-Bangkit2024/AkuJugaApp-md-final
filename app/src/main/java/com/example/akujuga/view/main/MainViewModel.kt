package com.example.akujuga.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.akujuga.data.UserRepository
import com.google.firebase.auth.FirebaseUser


class MainViewModel(private val repository: UserRepository) : ViewModel() {
    private val _currentUser = MutableLiveData<FirebaseUser?>()

    init {
        _currentUser.value = repository.getCurrentUser()
    }

    fun getCurrentUser(): LiveData<FirebaseUser?> {
        return _currentUser
    }
}