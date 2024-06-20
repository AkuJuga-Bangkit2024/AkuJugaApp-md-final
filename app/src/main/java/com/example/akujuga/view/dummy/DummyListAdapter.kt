package com.example.akujuga.view.dummy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.akujuga.databinding.ItemLayoutBinding

class DummyListAdapter(private val listItem: ArrayList<Dummy>) : RecyclerView.Adapter<DummyListAdapter.ListItemAdapter>() {

    private var filteredList: ArrayList<Dummy> = ArrayList(listItem)

    class ListItemAdapter(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAdapter {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemAdapter(binding)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ListItemAdapter, position: Int) {
        val (description, photo) = filteredList[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(photo)
                .into(ivItemImage)
            tvDescription.text = description
        }
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            ArrayList(listItem)
        } else {
            val resultList = ArrayList<Dummy>()
            for (dummy in listItem) {
                if (dummy.name.lowercase().contains(query.lowercase())) {
                    resultList.add(dummy)
                }
            }
            resultList
        }
        notifyDataSetChanged()
    }
}

