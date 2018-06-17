package com.example.misa.iwatch.ui.adapters

/**
 * Created by NAWAL on 28/03/2018.
 */

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.activities.MovieDetailActivity
import com.example.misa.iwatch.entity.Movie
import android.os.Bundle
import android.util.Log
import android.widget.*


class MoviesAdapter: PagedListAdapter<Movie,MoviesAdapter.ViewHolder>(Movie.DIFF_CALL) {
    private var context: Context? = null



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d("position ",position.toString())
        holder?.titre?.text = getItem(position)?.title
        Log.d("ITEM:","${position} : ${getItem(position)?.title}")
        holder?.info?.text = getItem(position)?.info
        holder?.directeur?.text = getItem(position)?.directeur
        //holder?.image?.setImageResource(getItem(position).image)
       // holder?.grade?.text =  moy(getItem(position)?.eval).toString().substring(0,3)



        holder?.details!!.setOnClickListener {

            val intent = Intent(context, MovieDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("film", getItem(position))
            bundle.putInt("index_movie", position)
            intent.putExtras(bundle)

            context!!.startActivity(intent)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
        context=v.context
        return ViewHolder(v)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val titre = itemView.findViewById<TextView>(R.id.title)
        val info = itemView.findViewById<TextView>(R.id.details)
        val directeur = itemView.findViewById<TextView>(R.id.directorName)
        val image = itemView.findViewById<ImageView>(R.id.picturePersonne)
        val details = itemView.findViewById<RelativeLayout>(R.id.btnMovieDetail)
        val grade= itemView.findViewById<Button>(R.id.grade)

    }
    fun moy(eval: ArrayList<Float>):Float{
        var star:Float= 0.0F

        for (value in eval ){
            star=star+value
        }
        star=star/eval.size

        return star;

    }
}