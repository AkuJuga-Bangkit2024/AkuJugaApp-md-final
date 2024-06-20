package com.example.akujuga.view.feature

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akujuga.R
import com.example.akujuga.databinding.ActivityNumberBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.adapter.AlphabetListAdapter
import com.example.akujuga.view.adapter.NumberListAdapter
import com.example.akujuga.view.dummy.Dummy
import com.example.akujuga.view.dummy.DummyNumberListAdapter

class NumberActivity : AppCompatActivity() {
    private val viewModel by viewModels<NumberViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityNumberBinding
//    private lateinit var adapter: NumberListAdapter
    private lateinit var adapter: DummyNumberListAdapter

    private val list = ArrayList<Dummy>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        list.addAll(getDummyList())
        setupAdapter()
        setupList()
        setupSearch()
    }

    private fun getDummyList(): ArrayList<Dummy> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo_number)
        val dataName = resources.getStringArray(R.array.data_number)
        val listItem = ArrayList<Dummy>()
        for (i in dataName.indices) {
            val hero = Dummy(dataName[i], dataPhoto.getResourceId(i, -1))
            listItem.add(hero)
        }
        return listItem
    }

    private fun setupAdapter() {
//        adapter = NumberListAdapter()
        adapter = DummyNumberListAdapter(list)
        binding.listItem.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.listItem.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listItem.addItemDecoration(itemDecoration)
    }

    private fun setupList() {
//        viewModel.numberResponse.observe(this) {
//            it?.let {
//                adapter.submitList(it.images)
//            }
//        }
//        viewModel.getNumber()
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