package com.example.akujuga.view.feature

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akujuga.R
import com.example.akujuga.databinding.ActivityKamusBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.dummy.Dummy
import com.example.akujuga.view.dummy.DummyListAdapter

class KamusActivity : AppCompatActivity() {
    private val viewModel by viewModels<KamusViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityKamusBinding
//    private lateinit var adapter: DictionaryListAdapter
    private lateinit var adapter: DummyListAdapter

    private val list = ArrayList<Dummy>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        list.addAll(getDummyList())
        setupAdapter()
        setupList()
        setupSearch()
    }

    private fun setupAdapter() {
//        adapter = DictionaryListAdapter()
        adapter = DummyListAdapter(list)
        binding.listItem.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.listItem.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listItem.addItemDecoration(itemDecoration)
    }

    private fun getDummyList(): ArrayList<Dummy> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo_dictionary)
        val dataName = resources.getStringArray(R.array.data_dictionary)
        val listItem = ArrayList<Dummy>()
        for (i in dataName.indices) {
            val hero = Dummy(dataName[i], dataPhoto.getResourceId(i, -1))
            listItem.add(hero)
        }
        return listItem
    }

    private fun setupList() {
//        viewModel.dictionaryResponse.observe(this) {
//            it?.let {
//                adapter.submitList(it.words)
//            }
//        }
    }

    private fun setupSearch() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        val query = searchView.text.toString()
                        adapter.filter(query)
                        searchBar.setText(query)
                        searchView.hide()
                        true
                    } else {
                        false
                    }
                }
        }
    }
}