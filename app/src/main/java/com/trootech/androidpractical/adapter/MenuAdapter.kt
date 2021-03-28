package com.trootech.androidpractical.adapter

import MenuModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trootech.androidpractical.R

class MenuAdapter(mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var inflate: LayoutInflater = LayoutInflater.from(mContext)
    private var listMenu = mutableListOf<MenuModel.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflate.inflate(R.layout.item_menu_child, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as Holder
        holder.tvChild.text = listMenu[position].nombre
    }

    fun setMenuItem(it: List<MenuModel.Data>) {
        listMenu = it as MutableList<MenuModel.Data>
        notifyDataSetChanged()
    }

    inner class Holder(item: View) : RecyclerView.ViewHolder(item) {
        var tvChild = item.findViewById(R.id.tv_child) as TextView
    }
}