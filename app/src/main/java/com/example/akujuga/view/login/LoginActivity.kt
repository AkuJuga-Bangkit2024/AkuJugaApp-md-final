package com.example.akujuga.view.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.akujuga.R
import com.example.akujuga.databinding.ActivityLoginBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.guest.GuestActivity
import com.example.akujuga.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupView()
        setupAction()
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

    private fun setupAction() {
        binding.signInButton.setOnClickListener {
            signInWithGoogle()
        }

        binding.guestLogin.setOnClickListener {
            signInAnonymously()
        }
    }

    private fun signInAnonymously() {
        viewModel.signInAnonymously(this) { user ->
            updateUIForGuest(user)
        }
    }

    private fun signInWithGoogle() {
        viewModel.signInWithGoogle(this) { user ->
            updateUI(user)
        }
    }

    private fun updateUIForGuest(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, GuestActivity::class.java))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            finish()
        } else {
            Toast.makeText(this, "Guest Login Failed.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = viewModel.getCurrentUser()
        updateUI(currentUser)
    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}
