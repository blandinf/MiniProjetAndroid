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
    lateinit var resultData:List<Article>

    companion object{
        lateinit var categoryChoice:String
        lateinit var countryChoice:String
        lateinit var sourceChoice:String
        lateinit var getModeChoice:String

        fun newInstance(category:String,getMode:String):ArticleFragment{
            categoryChoice = category
            getModeChoice = getMode
            return ArticleFragment()
        }

        fun newCountryInstance(country:String,getMode:String):ArticleFragment{
            countryChoice = country
            getModeChoice = getMode
            return ArticleFragment()
        }
        fun newSourceInstance(source:String,getMode:String):ArticleFragment{
            sourceChoice = source
            getModeChoice = getMode
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

            when(getModeChoice){
                "country"-> resultData = repository.getArticlesByCountry(countryChoice)
                "category"-> resultData = repository.getArticlesByCategory(categoryChoice)
                "sources"-> resultData = repository.getArticlesBySources(sourceChoice)
            }

            bindData(resultData)
        }
    }

    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            val adapterRecycler = ArticleAdapter(result) {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(it)
                startActivity(openURL)
            }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapterRecycler
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_list, container, false)
    }
}