package com.example.akujuga.view.feature

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akujuga.R
import com.example.akujuga.databinding.ActivityAlphabetBinding
import com.example.akujuga.view.ViewModelFactory
import com.example.akujuga.view.dummy.Dummy
import com.example.akujuga.view.dummy.DummyAlphabetListAdapter

class AlphabetActivity : AppCompatActivity() {
    private val viewModel by viewModels<AlphabetViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityAlphabetBinding
    private lateinit var adapter: DummyAlphabetListAdapter

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
        return listItem
    }

    private fun setupAdapter() {
        adapter = DummyAlphabetListAdapter(list)
        binding.listItem.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.listItem.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listItem.addItemDecoration(itemDecoration)

//        adapter.setOnItemClickCallback(object : DummyAlphabetListAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Dummy) {
//                Toast.makeText(this@AlphabetActivity, data.name, Toast.LENGTH_SHORT).show()
//            }
//        })
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
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(this@AlphabetActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }
}
