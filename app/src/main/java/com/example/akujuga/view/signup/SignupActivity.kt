package com.example.akujuga.view.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.akujuga.R
import com.example.akujuga.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}