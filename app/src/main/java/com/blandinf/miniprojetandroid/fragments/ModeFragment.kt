package com.blandinf.miniprojetandroid.fragments

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
import com.blandinf.miniprojetandroid.models.Mode

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
        //créer une liste d'articles
        val modes = listOf(
            Mode("Sources", "sources"),
            Mode("Catégories", "categories"),
            Mode("Pays", "countries")
        )
        //créer une instance de l'adapteur
        val adapterRecycler = ModeAdapter(modes) {
            Toast.makeText(context,"Mode name $it", Toast.LENGTH_LONG)
                .show()
            when (it) {
                "sources" -> activity?.change(SourceFragment())
                "categories" -> activity?.change(CategoryFragment())
                else -> activity?.change(CountryFragment())
            }
        }
        //définir l'orientation des élements (vertical)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        //associer l'adapter à la recyclerview
        recyclerView.adapter = adapterRecycler
    }
}