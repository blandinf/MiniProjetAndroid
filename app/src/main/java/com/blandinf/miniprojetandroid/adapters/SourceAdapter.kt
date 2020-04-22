package com.blandinf.miniprojetandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.miniprojetandroid.R
import com.blandinf.miniprojetandroid.models.Source
import com.bumptech.glide.Glide

class SourceAdapter(private val dataset: List<Source>) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Source) {
            val image = root.findViewById<ImageView>(R.id.source_image)
            val source = root.findViewById<TextView>(R.id.source_name)
            val description = root.findViewById<TextView>(R.id.source_description)
            Glide.with(root.context)
                .load(item.url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
            source.text = item.name
            description.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.source_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataset[position])

    override fun getItemCount(): Int = dataset.size
}