package com.example.misa.iwatch.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.misa.iwatch.R
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.Personnes
import com.example.misa.iwatch.ui.adapters.PersonnesAdapter
import com.example.misa.iwatch.entity.data.Companion.getActors
import com.example.misa.iwatch.entity.data.Companion.getRealisators
import com.example.misa.iwatch.ui.ViewModels.ActorsViewModel
import com.example.misa.iwatch.ui.ViewModels.MoviesViewModel
import com.example.misa.iwatch.utils.getViewModel

/**
 * Created by misa on 3/27/18.
 */
class PersonsFragment : Fragment() {
    private val personnesAdapter by lazy {
        PersonnesAdapter(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        Log.d("Here","================================")

        val rootView = inflater.inflate(R.layout.fragment_persons, container, false)
        val rv = rootView.findViewById<RecyclerView>(R.id.RecycleViewPersonnes)

        val btnActor = rootView.findViewById<Button>(R.id.btnActors)

        btnActor.setBackgroundResource(R.drawable.clicked_button)
        btnActor.setTextColor(Color.parseColor("#ffffff"))

        val btnRealistor = rootView.findViewById<Button>(R.id.btnRealisators)

        btnRealistor.setBackgroundResource(R.drawable.no_clicked_button)
        btnRealistor.setTextColor(Color.parseColor("#EF4B53"))

        btnActor.setOnClickListener {
            remplirActors(rv)

            btnActor.setBackgroundResource(R.drawable.clicked_button)
            btnActor.setTextColor(Color.parseColor("#ffffff"))

            btnRealistor.setBackgroundResource(R.drawable.no_clicked_button)
            btnRealistor.setTextColor(Color.parseColor("#EF4B53"))

        }
        btnRealistor.setOnClickListener {
            remplirRealisators(rv)

            btnActor.setBackgroundResource(R.drawable.no_clicked_button)
            btnActor.setTextColor(Color.parseColor("#EF4B53"))

            btnRealistor.setBackgroundResource(R.drawable.clicked_button)
            btnRealistor.setTextColor(Color.parseColor("#ffffff"))
        }

        val actorsViewModel = getViewModel(IRepository.Type.ACTORS) as ActorsViewModel
        actorsViewModel.popularActors.observe(this, Observer<PagedList<Personnes>> {
            personnesAdapter.submitList(it)
        })

        remplirActors(rv)// par defaut

        return rootView
    }
    fun remplirActors(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv.adapter = personnesAdapter


    }
    fun remplirRealisators(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Realisators= getRealisators()

        //var adapter = PersonnesAdapter(Realisators)
        //rv.adapter = adapter


    }
}