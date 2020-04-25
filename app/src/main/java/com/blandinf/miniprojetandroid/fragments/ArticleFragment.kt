package com.blandinf.miniprojetandroid.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.httpdatas.models.Article
import com.blandinf.httpdatas.repositories.ArticleRepository
import com.blandinf.miniprojetandroid.R
import com.blandinf.miniprojetandroid.adapters.ArticleAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArticleFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    private val repository = ArticleRepository()

    companion object{
        // mode selected by user between ["sources", "categories", "countries"]
        lateinit var selectedMode:String
        // user can filter articles according to the mode selected
        lateinit var selectedFilter:String

        fun newInstance(filter: String, getMode: String): ArticleFragment {
            selectedFilter = filter
            selectedMode = getMode
            return ArticleFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewArticle)

        lifecycleScope.launch {
            getData()
        }
    }

    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            bindData(repository.getArticles(selectedFilter, selectedMode))
        }
    }

    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            val adapterRecycler = ArticleAdapter(result, {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val shareSub = it
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
                startActivity(Intent.createChooser(shareIntent, "Share using"))
            }, {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(it)
                startActivity(openURL)
            })
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapterRecycler
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_list, container, false)
    }
}