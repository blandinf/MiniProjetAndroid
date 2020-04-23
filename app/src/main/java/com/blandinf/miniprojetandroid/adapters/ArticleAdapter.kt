package com.blandinf.miniprojetandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.httpdatas.models.Article
import com.blandinf.httpdatas.models.Source
import com.blandinf.miniprojetandroid.R
import com.bumptech.glide.Glide

class ArticleAdapter(private val dataset: List<Article>, private val callback: (mode: String) -> Unit) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val title = root.findViewById<TextView>(R.id.title)
            val description = root.findViewById<TextView>(R.id.description)
            val source = root.findViewById<TextView>(R.id.source_name)
            val image = root.findViewById<ImageView>(R.id.article_image)


            title.text = item.title
            description.text = item.description
            source.text = item.source.name

            Glide.with(root.context)
                .load(item.urlToImage)
                .centerCrop()
                .into(image)


            root.setOnClickListener {
                callback(item.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataset[position])

    override fun getItemCount(): Int = dataset.size
}