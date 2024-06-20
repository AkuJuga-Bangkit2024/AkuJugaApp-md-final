package com.example.akujuga.view.dummy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.akujuga.databinding.ItemLayoutBinding

class DummyNumberListAdapter(private val listItem: ArrayList<Dummy>) : RecyclerView.Adapter<DummyNumberListAdapter.ListItemAdapter>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListItemAdapter(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAdapter {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemAdapter(binding)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ListItemAdapter, position: Int) {
        val (description, photo) = listItem[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(photo)
                .into(ivItemImage)
            tvDescription.text = description
            root.setOnClickListener {
                onItemClickCallback?.onItemClicked(listItem[holder.adapterPosition])
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Dummy)
    }
}
