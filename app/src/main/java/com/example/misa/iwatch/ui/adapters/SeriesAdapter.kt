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
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.activities.SerieDetailActivity
import com.example.misa.iwatch.entity.Series
import kotlin.math.log

class SeriesAdapter: PagedListAdapter<Series,SeriesAdapter.ViewHolder>(Series.DIFF_CALL) {
    private var context: Context? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.titre?.text = getItem(position)?.titre
        Log.d("Series"," item is ${getItem(position)?.titre}")
        holder?.info?.text = getItem(position)?.info
        holder?.directeur?.text = getItem(position)?.directeur
       // holder?.image?.setImageResource(getItem(position)?.image)
       // holder?.grade?.text=moy(getItem(position)?.eval).toString().substring(0,3)





       holder?.details!!.setOnClickListener {

           val intent = Intent(context, SerieDetailActivity::class.java)
           val bundle = Bundle()
           bundle.putSerializable("serie", getItem(position))
           bundle.putInt("index_serie", position)
           intent.putExtras(bundle)

           context!!.startActivity(intent)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.serie_item, parent, false)
        context=v.context
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