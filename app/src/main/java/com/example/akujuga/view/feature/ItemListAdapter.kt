package com.example.akujuga.view.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.akujuga.data.remote.response.ImagesItem
import com.example.akujuga.databinding.ItemLayoutBinding

class ItemListAdapter: ListAdapter<ImagesItem, ItemListAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImagesItem) {
            binding.tvDescription.text = item.letter
            Glide
                .with(itemView.context)
                .load(item.imageUrl)
                .fitCenter()
                .into(binding.ivItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImagesItem>() {
            override fun areItemsTheSame(oldItem: ImagesItem, newItem: ImagesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ImagesItem, newItem: ImagesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}