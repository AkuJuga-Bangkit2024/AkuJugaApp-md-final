package com.example.akujuga.data

import com.example.akujuga.data.pref.UserModel
import com.example.akujuga.data.pref.UserPreference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val auth: FirebaseAuth
) {

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
   fun logout() {
        auth.signOut()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(auth: FirebaseAuth
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(auth)
            }.also { instance = it }
    }
}