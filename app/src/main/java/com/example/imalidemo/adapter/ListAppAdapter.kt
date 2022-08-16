package com.example.imalidemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.imalidemo.R
import com.example.imalidemo.entity.AppEntity

class ListAppAdapter(private val dataset: List<AppEntity>) :
    RecyclerView.Adapter<ListAppAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val image: ImageView

        init {
            name = view.findViewById(R.id.appName)
            image = view.findViewById(R.id.appImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayOut =
            LayoutInflater.from(parent.context).inflate(R.layout.app_tile, parent, false)
        return ItemViewHolder(adapterLayOut)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.name.text = dataset[position].appName
//        holder.image.setImageIcon(dataset[position].appIcon)
//        var imageDrawable= resour
        holder.image.setImageDrawable(dataset[position].appIcon)
//        holder.image.setImageResource(R.drawable.ic_launcher_background)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}