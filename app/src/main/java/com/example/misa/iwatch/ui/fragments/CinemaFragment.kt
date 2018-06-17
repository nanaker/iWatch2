package com.example.misa.iwatch.ui.fragments

/**
 * Created by misa on 3/27/18.
 */

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
import com.example.misa.iwatch.ui.adapters.CinemaAdapter
import com.example.misa.iwatch.ui.adapters.MoviesAdapter
import com.example.misa.iwatch.entity.data.Companion.getCinema
import com.example.misa.iwatch.entity.data.Companion.getMoviesRecent

class CinemaFragment : Fragment() {

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

        remplirMovies(rv)// par defaut
        return rootView
    }
    fun remplirMovies(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        //val Films= getMoviesRecent()

      //  var adapter = MoviesAdapter(Films)
     //   rv.adapter = adapter


    }
    fun remplirCinema(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Cinemas= getCinema()

        var adapter = CinemaAdapter(Cinemas)
        rv.adapter = adapter


    }


}