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
import com.example.misa.iwatch.ui.adapters.CinemaAdapter
import com.example.misa.iwatch.ui.adapters.MoviesAdapter
import com.example.misa.iwatch.ui.adapters.SeriesAdapter
import com.example.misa.iwatch.entity.data

/**
 * Created by NAWAL on 20/04/2018.
 */
class FunFragment: Fragment() {



        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_fun, container, false)


            val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewFun)


            val btnMovies = rootView.findViewById<Button>(R.id.btnmovies_fun)
            btnMovies.setBackgroundResource(R.drawable.clicked_fun_button)
            btnMovies.setTextColor(Color.parseColor("#ffffff"))

            val btnseries = rootView.findViewById<Button>(R.id.btnseries_fun)
            btnseries.setBackgroundResource(R.drawable.no_clicked_fun_button)
            btnseries.setTextColor(Color.parseColor("#EF4B53"))

            val btnRoom = rootView.findViewById<Button>(R.id.btnRooms_fun)
            btnRoom.setBackgroundResource(R.drawable.no_clicked_fun_button)
            btnRoom.setTextColor(Color.parseColor("#EF4B53"))

            btnMovies.setOnClickListener {
                remplirMovies(rv)

                btnMovies.setBackgroundResource(R.drawable.clicked_fun_button)
                btnMovies.setTextColor(Color.parseColor("#ffffff"))

                btnseries.setBackgroundResource(R.drawable.no_clicked_fun_button)
                btnseries.setTextColor(Color.parseColor("#EF4B53"))

                btnRoom.setBackgroundResource(R.drawable.no_clicked_fun_button)
                btnRoom.setTextColor(Color.parseColor("#EF4B53"))

            }

            btnseries.setOnClickListener {
                remplirSeries(rv)

                btnMovies.setBackgroundResource(R.drawable.no_clicked_fun_button)
                btnMovies.setTextColor(Color.parseColor("#EF4B53"))

                btnseries.setBackgroundResource(R.drawable.clicked_fun_button)
                btnseries.setTextColor(Color.parseColor("#ffffff"))

                btnRoom.setBackgroundResource(R.drawable.no_clicked_fun_button)
                btnRoom.setTextColor(Color.parseColor("#EF4B53"))
            }

            btnRoom.setOnClickListener {
                remplirRooms(rv)

                btnMovies.setBackgroundResource(R.drawable.no_clicked_fun_button)
                btnMovies.setTextColor(Color.parseColor("#EF4B53"))

                btnseries.setBackgroundResource(R.drawable.no_clicked_fun_button)
                btnseries.setTextColor(Color.parseColor("#EF4B53"))

                btnRoom.setBackgroundResource(R.drawable.clicked_fun_button)
                btnRoom.setTextColor(Color.parseColor("#ffffff"))
            }



            remplirMovies(rv)// par defaut


            return rootView

        }
    fun remplirMovies(rv:RecyclerView){

        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        val Films= data.getMoviesFav()
        //var adapter = MoviesAdapter(Films)
      //  rv.adapter = adapter

    }

    fun remplirSeries(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Series= data.getSerieFav()
       // var adapter = SeriesAdapter()
       // rv.adapter = adapter


    }

    fun remplirRooms(rv:RecyclerView){
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Rooms= data.getCinemaFav()
        var adapter = CinemaAdapter(Rooms)
        rv.adapter = adapter


    }



}