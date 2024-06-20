package com.example.akujuga.view.feature

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akujuga.R
import com.example.akujuga.databinding.ActivityAlphabetBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.dummy.Dummy
import com.example.akujuga.view.dummy.DummyListAdapter

class AlphabetActivity : AppCompatActivity() {
    private val viewModel by viewModels<AlphabetViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityAlphabetBinding
    private lateinit var adapter: DummyListAdapter

    private val list = ArrayList<Dummy>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlphabetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        list.addAll(getDummyList())
        setupAdapter()
        setupList()
        setupSearch()
    }

    private fun getDummyList(): ArrayList<Dummy> {
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataName = resources.getStringArray(R.array.data_name)
        val listItem = ArrayList<Dummy>()
        for (i in dataName.indices) {
            val hero = Dummy(dataName[i], dataPhoto.getResourceId(i, -1))
            listItem.add(hero)
        }
        dataPhoto.recycle()
        return listItem
    }

    private fun setupAdapter() {
        adapter = DummyListAdapter(list)
        binding.listItem.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.listItem.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listItem.addItemDecoration(itemDecoration)

    }

    private fun setupList() {
//        viewModel.alphabetResponse.observe(this) {
//            it?.let {
//                adapter.submitList(it.images)
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
