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
import com.blandinf.httpdatas.models.Country
import com.blandinf.miniprojetandroid.change

class CountryFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var countryChoice:String


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
            Country(
                "France",
                "fr",
                "https://images.emojiterra.com/twitter/v12/512px/1f1eb-1f1f7.png"
            ),
            Country(
                "Angleterre",
                "gb",
                "https://images.emojiterra.com/twitter/v12/512px/1f1ec-1f1e7.png"
            ),
            Country(
                "Allemagne",
                "de",
                "https://images.emojiterra.com/twitter/v12/512px/1f1e9-1f1ea.png"
            )
        )
        val adapterRecycler = CountryAdapter(countries) {

            countryChoice = it
            activity?.change(ArticleFragment.newCountryInstance(it,"country"))

        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapterRecycler
    }
}