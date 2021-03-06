package com.blandinf.miniprojetandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.miniprojetandroid.R
import com.blandinf.miniprojetandroid.adapters.SourceAdapter
import com.blandinf.httpdatas.models.Source
import com.blandinf.httpdatas.repositories.SourceRepository
import com.blandinf.miniprojetandroid.change
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SourceFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    private val repository = SourceRepository()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewSource)
        lifecycleScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val result = repository.getSources()
            bindData(result)

            val loadingText = view?.findViewById<TextView>(R.id.loadingText)
            if (loadingText != null) {
                loadingText.text = ""
            }
        }
    }
    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Source>) {
        withContext(Dispatchers.Main) {
            val adapterRecycler = SourceAdapter(result) {
                activity?.change(ArticleFragment.newInstance(it,"sources"))
            }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapterRecycler
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.source_list, container, false)
    }
}