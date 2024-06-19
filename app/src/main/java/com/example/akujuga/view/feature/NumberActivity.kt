package com.example.akujuga.view.feature

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akujuga.databinding.ActivityNumberBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.adapter.AlphabetListAdapter
import com.example.akujuga.view.adapter.NumberListAdapter

class NumberActivity : AppCompatActivity() {
    private val viewModel by viewModels<NumberViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityNumberBinding
    private lateinit var adapter: NumberListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAdapter()
        setupList()
        setupSearch()
    }

    private fun setupAdapter() {
        adapter = NumberListAdapter()
        binding.listItem.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.listItem.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listItem.addItemDecoration(itemDecoration)
    }

    private fun setupList() {
        viewModel.numberResponse.observe(this) {
            it?.let {
                adapter.submitList(it.images)
            }
        }
        viewModel.getNumber()
    }


    private fun setupSearch() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(this@NumberActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }
}