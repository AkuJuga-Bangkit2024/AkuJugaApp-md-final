package com.example.akujuga.view.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.akujuga.R
import com.example.akujuga.databinding.ActivityMainBinding
import com.example.akujuga.view.guest.GuestActivity
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = Firebase.auth
        val firebaseUser = auth.currentUser

        if (firebaseUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else if (firebaseUser.isAnonymous) {
            startActivity(Intent(this, GuestActivity::class.java))
            finish()
        }

//        setupView()
        setupBar()
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
    }

//    override fun onBackPressed() {
//        val navController = findNavController(R.id.nav_host_fragment)
//        if (!navController.popBackStack()) {
//            // Jika tidak ada fragmen lagi dalam back stack
//            super.onBackPressed()
//            finishAffinity() // Keluar dari aplikasi
//        }
//    }



    private fun setupBar() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_camera, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}