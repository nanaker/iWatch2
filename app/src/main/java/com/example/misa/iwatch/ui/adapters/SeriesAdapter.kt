package com.example.misa.iwatch.ui.adapters

/**
 * Created by NAWAL on 28/03/2018.
 */

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.activities.SerieDetailActivity
import com.example.misa.iwatch.entity.Series
import kotlin.math.log

class SeriesAdapter(private val context: Context): PagedListAdapter<Series,SeriesAdapter.ViewHolder>(Series.DIFF_CALL) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.titre?.text = getItem(position)?.titre
        Log.d("Series"," item is ${getItem(position)?.titre}")
        holder?.info?.text = getItem(position)?.info?.take(50)+"..."
        Glide.with(context)
                .load(getItem(position)?.image)
                .into(holder?.image)
        holder?.grade?.text=getItem(position)?.voteAverage.toString().substring(0,3)
        holder?.directeur?.text = getItem(position)?.date





       holder?.details!!.setOnClickListener {

           val intent = Intent(context, SerieDetailActivity::class.java)
           val bundle = Bundle()

           bundle.putInt("id_serie", getItem(position)?.id!!)
           intent.putExtras(bundle)

           context!!.startActivity(intent)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.serie_item, parent, false)
        return ViewHolder(v);
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val titre = itemView.findViewById<TextView>(R.id.titleSerie)
        val info = itemView.findViewById<TextView>(R.id.detailsSerie)
        val directeur = itemView.findViewById<TextView>(R.id.directorSerie)
        val image = itemView.findViewById<ImageView>(R.id.picturePersonneSerie)
        val details = itemView.findViewById<RelativeLayout>(R.id.btnSerieDetail)


        val grade = itemView.findViewById<Button>(R.id.gradeserie)


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