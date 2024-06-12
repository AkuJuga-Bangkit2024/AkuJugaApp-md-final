package com.example.akujuga.view.guest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.akujuga.R
import com.example.akujuga.databinding.ActivityGuestBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class GuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBar()

    }

    private fun setupBar() {
        val navView: BottomNavigationView = findViewById(R.id.guest_nav_view)
        val navController = findNavController(R.id.guest_nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_guest_home, R.id.navigation_guest_camera, R.id.navigation_guest_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}