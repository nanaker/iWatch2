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
import android.widget.*
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.activities.SerieDetailActivity
import com.example.misa.iwatch.entity.Series
import com.example.misa.iwatch.entity.associate_series

class SeriesLAdapter(val serieList: ArrayList<associate_series>): RecyclerView.Adapter<SeriesLAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.titre?.text = serieList[position].nom
        holder?.info?.text = serieList[position].info
      //  holder?.directeur?.text = serieList[position].directeu
        //holder?.image?.setImageResource(serieList[position].image)
        holder?.grade?.text=serieList[position].voteAverage.toString()


       holder?.details!!.setOnClickListener {

           val intent = Intent(context, SerieDetailActivity::class.java)
           val bundle = Bundle()
           bundle.putSerializable("serie", serieList[position].id)
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

    override fun getItemCount(): Int {
        return serieList.size
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