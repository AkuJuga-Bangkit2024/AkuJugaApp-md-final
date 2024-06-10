package com.example.akujuga.view.feature

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.akujuga.databinding.ActivityAlphabetBinding

class AlphabetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlphabetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlphabetBinding.inflate(layoutInflater)
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
                    Toast.makeText(this@AlphabetActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }
}