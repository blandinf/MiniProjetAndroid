package com.blandinf.miniprojetandroid.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.httpdatas.models.Mode
import com.blandinf.miniprojetandroid.R

class ModeAdapter(private val dataset: List<Mode>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ModeAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Mode, clickListener: OnItemClickListener) {
            val txtTitle = root.findViewById<TextView>(R.id.mode_name)
            txtTitle.text = item.name

            root.setOnClickListener {
                clickListener.onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mode_list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position], itemClickListener)
    }


    override fun getItemCount(): Int = dataset.size
}

interface OnItemClickListener{
    fun onItemClicked(mode: Mode)
}