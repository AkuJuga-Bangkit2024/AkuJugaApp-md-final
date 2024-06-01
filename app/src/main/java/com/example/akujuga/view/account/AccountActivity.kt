package com.example.akujuga.view.account

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.akujuga.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAnimation() {

        val title = ObjectAnimator.ofFloat(binding.tvAccountInfo, View.ALPHA, 1f).setDuration(150)
        val username = ObjectAnimator.ofFloat(binding.username, View.ALPHA, 1f).setDuration(150)
        val email = ObjectAnimator.ofFloat(binding.email, View.ALPHA, 1f).setDuration(150)
        val pass = ObjectAnimator.ofFloat(binding.password, View.ALPHA, 1f).setDuration(150)

        AnimatorSet().apply {
            playSequentially(title, username, email, pass)
            startDelay = 100
        }.start()
    }
}