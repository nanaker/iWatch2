package com.example.misa.iwatch.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.graphics.Color
import com.example.misa.iwatch.entity.Movie
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.misa.iwatch.R
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.LinearLayout
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.Repository.sieries.SeriesRepository
import com.example.misa.iwatch.entity.Series
import com.example.misa.iwatch.ui.adapters.MoviesAdapter
import com.example.misa.iwatch.ui.adapters.SeriesAdapter
import com.example.misa.iwatch.entity.data.Companion.getMoviesRecent
import com.example.misa.iwatch.entity.data.Companion.getSeriesRecent
import com.example.misa.iwatch.ui.ViewModels.ActorsViewModel
import com.example.misa.iwatch.ui.ViewModels.MoviesViewModel
import com.example.misa.iwatch.ui.ViewModels.SeriesViewModel
import com.example.misa.iwatch.utils.ServiceLocator
import com.example.misa.iwatch.utils.getViewModel


/**
 * Created by misa on 3/27/18.
 */
class HomeFragment: Fragment() {

    private lateinit var rootView: View
    private lateinit var  recyclerView:RecyclerView
    private val moviesAdapter by lazy {
        MoviesAdapter(context!!)
    }
    private val seriesAdapter by lazy {
        SeriesAdapter(context!!)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)!!


        val recyclerView = this.rootView.findViewById<RecyclerView>(R.id.recycleViewHome)
        val btnMovies= this.rootView.findViewById<Button>(R.id.btnmovies)
        val btnseries= this.rootView.findViewById<Button>(R.id.btnseries)

        btnMovies.setBackgroundResource(R.drawable.clicked_button)
        btnMovies.setTextColor(Color.parseColor("#ffffff"))
        btnseries.setBackgroundResource(R.drawable.no_clicked_button)
        btnseries.setTextColor(Color.parseColor("#EF4B53"))

        val moviesModel = getViewModel(IRepository.Type.MOVIE) as MoviesViewModel
        moviesModel.filmsOnTheater.observe(this, Observer<PagedList<Movie>> {
            moviesAdapter.submitList(it)
        })

        val seriesModel = getViewModel(IRepository.Type.SERIES) as SeriesViewModel
        seriesModel.seriesOnTheAir.observe(this, Observer<PagedList<Series>> {
            seriesAdapter?.submitList(it)
        })

        btnMovies.setOnClickListener {
            remplirMovies(recyclerView)

            btnMovies.setBackgroundResource(R.drawable.clicked_button)
            btnMovies.setTextColor(Color.parseColor("#ffffff"))

            btnseries.setBackgroundResource(R.drawable.no_clicked_button)
            btnseries.setTextColor(Color.parseColor("#EF4B53"))
        }

        btnseries.setOnClickListener {
            remplirSeries(recyclerView)

            btnMovies.setBackgroundResource(R.drawable.no_clicked_button)
            btnMovies.setTextColor(Color.parseColor("#EF4B53"))

            btnseries.setBackgroundResource(R.drawable.clicked_button)
            btnseries.setTextColor(Color.parseColor("#ffffff"))

        }

        remplirMovies(recyclerView)

        return rootView

    }


    fun remplirMovies(rv:RecyclerView){

        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv.adapter = moviesAdapter

    }

    fun remplirSeries(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        rv.adapter = seriesAdapter


    }


}