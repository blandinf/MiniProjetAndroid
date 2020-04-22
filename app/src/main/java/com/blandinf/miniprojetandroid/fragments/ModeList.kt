package com.blandinf.miniprojetandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.httpdatas.models.Mode
import com.blandinf.miniprojetandroid.R
import com.blandinf.miniprojetandroid.adapters.ModeAdapter
import com.blandinf.miniprojetandroid.adapters.OnItemClickListener

class ModeList: Fragment(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mode_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        bindRecyclerView()
    }

    override fun onItemClicked(mode: Mode) {
        Toast.makeText(context,"User name ${mode.name}", Toast.LENGTH_LONG)
            .show()
        //activity?.change(ListArticleFragment.newInstance(mode.name))
    }

    private fun bindRecyclerView() {
        //créer une liste d'articles
        val modes = listOf<Mode>(
            Mode(name="Sources"),
            Mode(name="Catégories"),
            Mode(name="Pays")
        )
        //créer une instance de l'adapteur
        val adapterRecycler = ModeAdapter(modes, this)
        //définir l'orientation des élements (vertical)
        recyclerView.layoutManager =
            LinearLayoutManager(context)
        //associer l'adapter à la recyclerview
        recyclerView.adapter = adapterRecycler
    }
}