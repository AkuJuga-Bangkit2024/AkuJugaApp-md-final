package com.example.akujuga.view.feature

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akujuga.databinding.ActivityKamusBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.adapter.AlphabetListAdapter
import com.example.akujuga.view.adapter.DictionaryListAdapter

class KamusActivity : AppCompatActivity() {
    private val viewModel by viewModels<KamusViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityKamusBinding
    private lateinit var adapter: DictionaryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAdapter()
        setupList()
        setupSearch()
    }

    private fun setupAdapter() {
        adapter = DictionaryListAdapter()
        binding.listItem.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.listItem.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listItem.addItemDecoration(itemDecoration)
    }

    private fun setupList() {
        viewModel.dictionaryResponse.observe(this) {
            it?.let {
                adapter.submitList(it.words)
            }
        }
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