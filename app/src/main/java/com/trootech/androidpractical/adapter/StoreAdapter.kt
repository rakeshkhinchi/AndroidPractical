package com.trootech.androidpractical.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trootech.androidpractical.R
import com.trootech.androidpractical.model.StoreModel

class StoreAdapter(val callback: (StoreModel.Franquicia) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listStore = mutableListOf<StoreModel.Franquicia>()

    fun setStoreList(listStore: List<StoreModel.Franquicia>) {
        this.listStore = listStore.toMutableList()
        notifyDataSetChanged()
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store, null)
        return MainViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return listStore.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as MainViewHolder
        holder.tvTitle.text = listStore[position].negocio

        holder.itemView.setOnClickListener {
            callback.invoke(listStore[position])
        }
    }

    inner class MainViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {
        var tvTitle = binding.findViewById(R.id.tv_title) as TextView
    }
}
