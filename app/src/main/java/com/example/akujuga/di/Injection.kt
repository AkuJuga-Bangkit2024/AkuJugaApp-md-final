package com.example.akujuga.di

import android.content.Context
import com.example.akujuga.data.UserRepository
import com.example.akujuga.data.pref.UserPreference
import com.example.akujuga.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}