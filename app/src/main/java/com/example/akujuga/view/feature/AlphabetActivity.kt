package com.example.akujuga.view.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.akujuga.databinding.ActivityAlphabetBinding

class AlphabetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlphabetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlphabetBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}