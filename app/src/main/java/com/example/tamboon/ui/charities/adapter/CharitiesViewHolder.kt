package com.example.tamboon.ui.charities.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tamboon.data.model.CharityModel
import kotlinx.android.synthetic.main.item_charities.view.*

class CharitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(item: CharityModel) = itemView.apply {
        Glide.with(this)
            .asGif()
            .load(item.logoUrl)
            .into(imageView_logo)
        textView_name.text = item.name
    }

}