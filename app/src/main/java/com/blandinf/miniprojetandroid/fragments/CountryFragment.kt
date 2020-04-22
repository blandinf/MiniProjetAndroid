package com.blandinf.miniprojetandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.miniprojetandroid.R
import com.blandinf.miniprojetandroid.adapters.CountryAdapter
import com.blandinf.miniprojetandroid.models.Country

class CountryFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewCountry)
        bindRecyclerView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.country_list, container, false)
    }

    private fun bindRecyclerView() {
        val countries = listOf(
            Country("France", "fr", "https://www.betapolitique.fr/wp-content/uploads/2019/05/definition-politique.jpg"),
            Country("Angleterre", "en", "https://www.betapolitique.fr/wp-content/uploads/2019/05/definition-politique.jpg"),
            Country("Allemagne", "de", "https://www.betapolitique.fr/wp-content/uploads/2019/05/definition-politique.jpg")
        )
        val adapterRecycler = CountryAdapter(countries) {
//            activity?.switchToFragment(ArticlesFragment.newInstance(it))
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapterRecycler
    }
}