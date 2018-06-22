package com.example.misa.iwatch.ui.fragments

/**
 * Created by misa on 3/27/18.
 */

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.graphics.Color
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.misa.iwatch.R
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.ui.adapters.CinemaAdapter
import com.example.misa.iwatch.ui.adapters.MoviesAdapter
import com.example.misa.iwatch.entity.data.Companion.getCinema
import com.example.misa.iwatch.ui.ViewModels.MoviesViewModel
import com.example.misa.iwatch.utils.getViewModel

class CinemaFragment : Fragment() {
    private val moviesAdapter by lazy {
        MoviesAdapter(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_cinema, container, false)

        val rv = rootView.findViewById<RecyclerView>(R.id.RecycleViewCinemas)

        val btnMovies=rootView.findViewById<Button>(R.id.btnMoviesCinema)
        btnMovies.setBackgroundResource(R.drawable.clicked_button)
        btnMovies.setTextColor(Color.parseColor("#ffffff"))

        val btnRooms=rootView.findViewById<Button>(R.id.btnRooms)
        btnRooms.setBackgroundResource(R.drawable.no_clicked_button)
        btnRooms.setTextColor(Color.parseColor("#EF4B53"))

        btnMovies.setOnClickListener {
            remplirMovies(rv)

            btnMovies.setBackgroundResource(R.drawable.clicked_button)
            btnMovies.setTextColor(Color.parseColor("#ffffff"))

            btnRooms.setBackgroundResource(R.drawable.no_clicked_button)
            btnRooms.setTextColor(Color.parseColor("#EF4B53"))

        }

        btnRooms.setOnClickListener {
            remplirCinema(rv)

            btnMovies.setBackgroundResource(R.drawable.no_clicked_button)
            btnMovies.setTextColor(Color.parseColor("#EF4B53"))

            btnRooms.setBackgroundResource(R.drawable.clicked_button)
            btnRooms.setTextColor(Color.parseColor("#ffffff"))

        }

        remplirMovies(rv)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val moviesModel = getViewModel(IRepository.Type.MOVIE) as MoviesViewModel
        moviesModel.popularFilms.observe(this, Observer<PagedList<Movie>> {
            moviesAdapter.submitList(it)
        })
    }
    fun remplirMovies(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv.adapter = moviesAdapter


    }
    fun remplirCinema(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Cinemas= getCinema()

        var adapter = CinemaAdapter(Cinemas)
        rv.adapter = adapter


    }


}