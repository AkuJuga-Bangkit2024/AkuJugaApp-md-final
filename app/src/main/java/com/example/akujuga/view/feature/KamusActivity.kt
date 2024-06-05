package com.example.akujuga.view.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.akujuga.databinding.ActivityKamusBinding

class KamusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKamusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}