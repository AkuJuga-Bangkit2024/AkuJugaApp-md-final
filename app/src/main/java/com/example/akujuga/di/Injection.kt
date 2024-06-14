package com.example.akujuga.di

import android.content.Context
import com.example.akujuga.data.UserRepository
import com.example.akujuga.data.pref.UserPreference
import com.example.akujuga.data.pref.dataStore
import com.google.firebase.auth.FirebaseAuth

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val auth = FirebaseAuth.getInstance()
        return UserRepository.getInstance(auth)
    }
}