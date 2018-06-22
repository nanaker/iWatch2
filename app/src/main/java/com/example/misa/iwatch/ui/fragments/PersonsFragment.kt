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


        val rootView = inflater.inflate(R.layout.fragment_persons, container, false)
        val rv = rootView.findViewById<RecyclerView>(R.id.RecycleViewPersonnes)


        remplirActors(rv)
        val actorsViewModel = getViewModel(IRepository.Type.ACTORS) as ActorsViewModel
        actorsViewModel.popularActors.observe(this, Observer<PagedList<Personnes>> {
            personnesAdapter.submitList(it)
        })


        return rootView
    }
    fun remplirActors(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv.adapter = personnesAdapter


    }

}