package com.example.akujuga.view.fragment.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akujuga.data.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {

    private val _currentUser = MutableLiveData<FirebaseUser?>()

    init {
        _currentUser.value = repository.getCurrentUser()
    }

    fun getCurrentUser(): LiveData<FirebaseUser?> {
        return _currentUser
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
            _currentUser.postValue(null) // Update the current user to null on logout
        }
    }
}