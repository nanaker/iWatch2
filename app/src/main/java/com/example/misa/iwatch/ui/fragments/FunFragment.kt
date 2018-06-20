package com.example.misa.iwatch.ui.fragments

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.CinemaAdapter
import com.example.misa.iwatch.entity.data
import com.example.misa.iwatch.room.adapter.FilmFavAdapter
import com.example.misa.iwatch.room.filmdb.filmDataBase
import com.example.misa.iwatch.room.filmdb.modal.film
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 * Created by NAWAL on 20/04/2018.
 */
class FunFragment: Fragment() {

    private lateinit var textViewMsg: TextView
    private  var filmDatabase: filmDataBase? = null
    private lateinit var films: List<film>
    private lateinit var rootView: View

    companion object {
        private lateinit var filmsAdapter: FilmFavAdapter
        private lateinit var recyclerView: RecyclerView
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            rootView = inflater.inflate(R.layout.fragment_fun, container, false)


            val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewFun)
            filmsAdapter = FilmFavAdapter(context!!)
            displayList()



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


    // ROOM


    private fun displayList() {
        filmDatabase = filmDataBase.getInstance(context!!)
        RetrieveTask(this).execute()
    }

    private class RetrieveTask// only retain a weak reference to the activity
    internal constructor(context: FunFragment) : AsyncTask<Void, Void, List<film>>() {

        private val activityReference: WeakReference<FunFragment>

        init {
            activityReference = WeakReference<FunFragment>(context)
        }

        override fun doInBackground(vararg voids: Void): List<film>? {
            return if (activityReference.get() != null)
                activityReference.get()!!.filmDatabase!!.getFilmDao().getFilmFav()
            else
                null
        }

        override fun onPostExecute(films: List<film>?) {
            if (films != null && films.size > 0) {
                filmsAdapter.list = films
                recyclerView.setAdapter(filmsAdapter)
                // hides empty text view
                activityReference.get()!!.textViewMsg!!.setVisibility(View.GONE)
              // activityReference.get()!!.filmsAdapter!!.notifyDataSetChanged()
            }
        }
    }






    fun onFilmFavClick(pos: Int) {

    }


     override fun onDestroy() {
        filmDataBase.destroyInstance()
        super.onDestroy()
    }
    // fin ROOM


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