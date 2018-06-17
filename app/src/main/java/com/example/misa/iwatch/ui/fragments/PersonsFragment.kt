package com.example.misa.iwatch.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.PersonnesAdapter
import com.example.misa.iwatch.entity.data.Companion.getActors
import com.example.misa.iwatch.entity.data.Companion.getRealisators

/**
 * Created by misa on 3/27/18.
 */
class PersonsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

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

        remplirActors(rv)// par defaut

        return rootView
    }
    fun remplirActors(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Actors= getActors()

        var adapter = PersonnesAdapter(Actors)
        rv.adapter = adapter


    }
    fun remplirRealisators(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Realisators= getRealisators()

        var adapter = PersonnesAdapter(Realisators)
        rv.adapter = adapter


    }
}