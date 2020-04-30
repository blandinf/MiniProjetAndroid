package com.blandinf.miniprojetandroid.fragments

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.miniprojetandroid.R
import com.blandinf.miniprojetandroid.adapters.ModeAdapter
import com.blandinf.miniprojetandroid.change
import com.blandinf.httpdatas.models.Mode

class ModeFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.mode_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewMode)
        bindRecyclerView()
    }

    private fun bindRecyclerView() {
        // Création de la liste de sources
        val modes = listOf(
            Mode("Par sources", "sources", R.drawable.sources_bg),
            Mode("Par catégories", "categories", R.drawable.categories_bg),
            Mode("Par pays", "countries", R.drawable.country_bg)
        )

        // Création d'une une instance de l'adapteur
        val adapterRecycler = ModeAdapter(modes) {
            when (it) {
                "sources" -> activity?.change(SourceFragment())
                "categories" -> activity?.change(CategoryFragment())
                else -> activity?.change(CountryFragment())
            }
        }
        // Définition de l'orientation des éléments
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        // Associer l'adapteur à la recyclerview
        recyclerView.adapter = adapterRecycler
    }
}