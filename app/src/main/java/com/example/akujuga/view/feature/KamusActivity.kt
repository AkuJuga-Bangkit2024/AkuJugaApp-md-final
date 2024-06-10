package com.example.akujuga.view.feature

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.akujuga.databinding.ActivityKamusBinding

class KamusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKamusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupSearch()
    }

    private fun setupSearch() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(this@KamusActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }
}