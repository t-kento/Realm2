package com.example.realm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*

class CustomAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ListObject>()

    var callback: CustomAdapterCallback? = null

    fun refresh(list: List<ListObject>) {
        items.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            onBindViewHolder(holder, position)
    }

    private fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = items[position]
        holder.apply {
            textView.text = data.title
            deleteButton.setOnClickListener {
                callback?.onClick(data)
            }
        }
    }

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.textView
        val deleteButton = view.deleteButton
    }

    interface CustomAdapterCallback {
        fun onClick(data: ListObject)
    }
}