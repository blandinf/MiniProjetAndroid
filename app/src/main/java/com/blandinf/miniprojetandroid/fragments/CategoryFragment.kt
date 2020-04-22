package com.blandinf.miniprojetandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blandinf.miniprojetandroid.R
import com.blandinf.miniprojetandroid.adapters.CategoryAdapter
import com.blandinf.httpdatas.models.Category
import com.blandinf.miniprojetandroid.change

class CategoryFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    companion object{

        lateinit var categoryChoice:String

        fun newInstance(category:String):ArticleFragment{
            categoryChoice = category
            return ArticleFragment()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewCategory)
        bindRecyclerView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.category_list, container, false)
    }

    private fun bindRecyclerView() {
        val categories = listOf(
            Category(title="Politique", name="politics", description="Gestion du coronavirus, tensions après les municipales, comment Macron gère tout cette crise actuelle en France", url="https://images.sudouest.fr/2019/01/31/5c53448666a4bd53506a492b/widescreen/1000x500/emmanuel-macron-dans.jpg?v1"),
            Category(title="Economie", name="economy", description="Le premier ministre l'a annoncé, la pire récession depuis 1945 aura lieu après cette crise du coronavirus.. aie !", url="https://www.actu-economie.com/wp-content/uploads/2018/10/L-economie-francaise-repart-bel-et-bien-mais-min-min.jpg"),
            Category(title="Education", name="education", description="Reprise de l'école le 11 mai, comment l'état va gérer la distanciation pour les plus petits, affaire à suivre !", url="https://3er1viui9wo30pkxh1v2nh4w-wpengine.netdna-ssl.com/wp-content/uploads/sites/113/2016/11/Minecraft-3-1024x683.jpg"),
            Category(title="Pandémie", name="pandemic", description="Le coronavirus est dans toutes les têtes, comment allons-nous sortir efficacement de cette pandémie mondiale ?", url="https://m1.quebecormedia.com/emp/emp/asdasdasdasddasdasdf8283d01-5947-42a8-977c-6b0cf2a66a4c_ORIGINAL.jpg?impolicy=crop-resize&x=0&y=0&w=0&h=0&width=650&height=650"),
            Category(title="Sciences", name="sciences", description="Le professeur Raoult est au centre de toutes les conversations, alors chloroquine, efficace ou simple illusion ?", url="https://img.huffingtonpost.com/asset/5c92d938220000c7001b4e98.jpeg?ops=scalefit_630_noupscale"),
            Category(title="Ecologie", name="ecology", description="Notre terre respire, les populations mondiales respirent un air plus pur, disons merci au point positif du corona !", url="https://static.actu.fr/uploads/2019/05/AdobeStock_112721620-854x567.jpeg")
        )
        val adapterRecycler = CategoryAdapter(categories) {
            // it = category name (ex: politics)
            //categoryChoice = it
            //activity?.change(SourceFragment())
            //activity?.change(newInstance(categoryChoice))


        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapterRecycler
    }
}