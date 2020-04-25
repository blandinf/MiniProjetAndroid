package com.blandinf.miniprojetandroid.adapters

import android.app.PendingIntent.getActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.miniprojetandroid.R
import com.blandinf.httpdatas.models.Mode

class ModeAdapter(private val dataset: List<Mode>, private val callback: (mode: String) -> Unit) : RecyclerView.Adapter<ModeAdapter.ViewHolder>() {

    inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Mode) {
            val title = root.findViewById<TextView>(R.id.mode_title)
            val background = root.findViewById<ImageView>(R.id.mode_bg)
            title.text = item.title
            background.setImageResource(item.background)


            root.setOnClickListener {
                callback(item.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mode_list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }


    override fun getItemCount(): Int = dataset.size
}

interface OnItemClickListener{
    fun onItemClicked(modeName: String)
}