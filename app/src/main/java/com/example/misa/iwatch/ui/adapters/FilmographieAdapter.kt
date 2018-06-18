package com.example.misa.iwatch.ui.adapters

/**
 * Created by NAWAL on 28/03/2018.
 */

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.activities.MovieDetailActivity
import com.example.misa.iwatch.entity.*
import com.example.misa.iwatch.entity.data.Companion.getMoviesRecent

class FilmographieAdapter(val associateFilmList: ArrayList<associate_Movie>): RecyclerView.Adapter<FilmographieAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.name?.text = associateFilmList[position].title
       // holder?.image?.setImageResource(associateFilmList[position].image)

        holder?.detail?.setOnClickListener {

            val intent = Intent(context, MovieDetailActivity::class.java)
            val bundle = Bundle()
            val films= getMoviesRecent()
            bundle.putSerializable("film", associateFilmList[position].id)
            intent.putExtras(bundle)

            context!!.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.filmographie_item, parent, false)
        context=v.context
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return associateFilmList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val name = itemView.findViewById<TextView>(R.id.filmographie_name)
        val image = itemView.findViewById<ImageView>(R.id.filmographie_picture)
        val detail= itemView.findViewById<LinearLayout>(R.id.btnDetailfilmographie)



    }
}