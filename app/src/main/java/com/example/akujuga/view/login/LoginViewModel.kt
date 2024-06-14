package com.example.akujuga.view.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akujuga.data.UserRepository
import com.example.akujuga.data.pref.UserModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    fun signInAnonymously(context: Context, onResult: (FirebaseUser?) -> Unit) {
        viewModelScope.launch {
            val user = repository.signInAnonymously(context)
            onResult(user)
        }
    }

    fun signInWithGoogle(context: Context, onResult: (FirebaseUser?) -> Unit) {
        viewModelScope.launch {
            val user = repository.signInWithGoogle(context)
            onResult(user)
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return repository.getCurrentUser()
    }
}