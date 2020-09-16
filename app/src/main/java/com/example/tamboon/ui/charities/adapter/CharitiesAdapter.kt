package com.example.tamboon.ui.charities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamboon.R
import com.example.tamboon.data.model.CharityModel

class CharitiesAdapter : RecyclerView.Adapter<CharitiesViewHolder>() {

    private var item: List<CharityModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_charities, parent, false
        )
        return CharitiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharitiesViewHolder, position: Int) {
        holder.bindData(item[position])
    }

    override fun getItemCount() = item.size

    fun setData(item: List<CharityModel>) {
        this.item = item
        notifyDataSetChanged()
    }
}