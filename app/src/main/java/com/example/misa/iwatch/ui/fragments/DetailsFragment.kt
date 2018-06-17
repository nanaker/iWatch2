package com.example.misa.iwatch.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.ActorsDetailAdapter
import com.example.misa.iwatch.ui.adapters.AssociateFilmAdapter
import com.example.misa.iwatch.entity.Movie

/**
 * Created by misa on 3/29/18.
 */
class DetailsFragment: android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val film = this.arguments?.getSerializable("detail") as  Movie


        val rootView = inflater.inflate(R.layout.fragment_details, container, false)

        val rv_actor = rootView.findViewById<RecyclerView>(R.id.RecycleViewActorDetail)
        rv_actor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)



        val nom = rootView.findViewById<TextView>(R.id.RealisatorDetailName)

        val image = rootView.findViewById<ImageView>(R.id.RealisatorDetailPicture)
        nom?.text=film.realisateur.nom
        image?.setImageResource(film.realisateur.image)

        var adapter_actor = ActorsDetailAdapter(film.actors)
        rv_actor.adapter = adapter_actor

        val rv_associateFilm = rootView.findViewById<RecyclerView>(R.id.RecycleViewAssociateFilm)
        rv_associateFilm.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        var adapter_associateFil = AssociateFilmAdapter(film.associatefilm)
        rv_associateFilm.adapter = adapter_associateFil

        return rootView
    }


    companion object {

        fun newInstance( film : Movie): DetailsFragment {

            val args = Bundle()

            args.putSerializable("detail", film)

            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}