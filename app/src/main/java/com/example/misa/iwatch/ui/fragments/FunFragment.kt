package com.example.misa.iwatch.ui.fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

    //private lateinit var textViewMsg: TextView
    private  var filmDatabase: filmDataBase? = null
    private lateinit var films: List<film>
    private lateinit var rootView: View

    companion object {
        private lateinit var filmsAdapter: FilmFavAdapter
        private lateinit var recyclerView: RecyclerView
        private lateinit var ctext: Context

        fun remplirMovies(rv:RecyclerView, films: List<film>?){
            var adapter = FilmFavAdapter(films!!, ctext!!)
            rv.adapter = adapter

        }
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            rootView = inflater.inflate(R.layout.fragment_fun, container, false)

            ctext = context!!
            recyclerView = rootView.findViewById<RecyclerView>(R.id.recycleViewFun)

            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            films = ArrayList<film>()
            filmsAdapter = FilmFavAdapter(films, context!!)
            recyclerView.adapter = filmsAdapter
            displayList()

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
            println("hellllllo from background")
            return if (activityReference.get() != null)
                activityReference.get()!!.filmDatabase!!.getFilmDao().getFilmFav()
            else
                null
        }

        override fun onPostExecute(films: List<film>?) {
            /**
             *  remplir les film dans cet appel !!!!!!!!
             * */
            val j = films!!.size
            Log.e("ID ", "Moooovie: $j")

            remplirMovies(recyclerView, films)


        }
    }


    fun onFilmFavClick(pos: Int) {

    }


     override fun onDestroy() {
        filmDataBase.destroyInstance()
        super.onDestroy()
    }
    // fin ROOM


    fun remplirMovies(rv:RecyclerView, films: List<film>?){

        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        val Films = data.getMoviesFav()
        var adapter = FilmFavAdapter(films!!, context!!)
       rv.adapter = adapter

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