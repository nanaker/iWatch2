package com.example.misa.iwatch.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.*
import com.example.misa.iwatch.entity.*

/**
 * Created by misa on 3/27/18.
 */
class FilmographieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_filmographie, container, false)
        val films = this.arguments?.getSerializable("filmographie")as ArrayList<Film>
        val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewFilmographie)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)




        var adapter = FilmographieAdapter(films)
        rv.adapter = adapter
        return rootView
    }
    companion object {

        fun newInstance( filmographie : ArrayList<Film>): FilmographieFragment {

            val args = Bundle()

            args.putSerializable("filmographie", filmographie)

            val fragment = FilmographieFragment()
            fragment.arguments = args
            return fragment
        }
    }
}