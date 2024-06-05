package com.example.akujuga.view.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.akujuga.databinding.ActivityNumberBinding

class NumberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}