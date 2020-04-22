package com.blandinf.miniprojetandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.miniprojetandroid.R
import com.blandinf.httpdatas.models.Country
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule

@GlideModule
class CountryAdapter(private val dataset: List<Country>, private val callback: (country: String) -> Unit) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Country) {
            val image = root.findViewById<ImageView>(R.id.country_image)
            val name = root.findViewById<TextView>(R.id.country_name)

            Glide.with(root.context)
                .load(item.url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
            name.text = item.name

            root.setOnClickListener {
                callback(item.language)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataset[position])

    override fun getItemCount(): Int = dataset.size
}