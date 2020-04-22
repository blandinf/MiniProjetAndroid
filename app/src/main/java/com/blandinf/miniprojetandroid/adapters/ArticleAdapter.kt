package com.blandinf.miniprojetandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.httpdatas.models.Article
import com.blandinf.httpdatas.models.Source
import com.blandinf.miniprojetandroid.R

class ArticleAdapter(private val dataset: List<Article>, private val callback: (mode: String) -> Unit) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val title = root.findViewById<TextView>(R.id.article_title)
            val description = root.findViewById<TextView>(R.id.source_description)

            title.text = item.title
            description.text = item.description

            root.setOnClickListener {
                callback(item.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.source_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataset[position])

    override fun getItemCount(): Int = dataset.size
}