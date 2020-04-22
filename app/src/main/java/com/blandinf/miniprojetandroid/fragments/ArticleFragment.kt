package com.blandinf.miniprojetandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.httpdatas.models.Article
import com.blandinf.httpdatas.models.Source
import com.blandinf.httpdatas.repositories.ArticleRepository
import com.blandinf.httpdatas.repositories.SourceRepository
import com.blandinf.miniprojetandroid.R
import com.blandinf.miniprojetandroid.adapters.ArticleAdapter
import com.blandinf.miniprojetandroid.adapters.SourceAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleFragment: Fragment() {
    lateinit var recyclerView: RecyclerView
    private val repository = ArticleRepository()

    companion object{

        lateinit var categoryChoice:String

        fun newInstance(category:String):ArticleFragment{
            categoryChoice = category
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
            val result = repository.getArticles(categoryChoice)
            bindData(result)
        }
    }
    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            val adapterRecycler = ArticleAdapter(result) {
                // it = source name (ex: bbc-news)
                // activity?.change(SourceFragment())

            }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapterRecycler
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_list, container, false)
    }
}