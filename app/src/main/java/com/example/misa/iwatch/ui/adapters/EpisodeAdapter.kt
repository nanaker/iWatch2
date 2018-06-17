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
import com.example.misa.iwatch.ui.activities.EpisodeDetailActivity
import com.example.misa.iwatch.entity.Episode

class EpisodeAdapter(val episodeList: ArrayList<Episode>): RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.titre?.text =episodeList[position].nom
        holder?.info?.text = episodeList[position].duree
        holder?.image?.setImageResource(episodeList[position].image)




       holder?.details!!.setOnClickListener {

           val intent = Intent(context,EpisodeDetailActivity::class.java)
           val bundle = Bundle()
           bundle.putSerializable("episode", episodeList[position])
           intent.putExtras(bundle)

           context!!.startActivity(intent)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.episode_item, parent, false)
        context=v.context
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return episodeList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val titre = itemView.findViewById<TextView>(R.id.titleepisode)
        val info = itemView.findViewById<TextView>(R.id.detailepisode)
        val image = itemView.findViewById<ImageView>(R.id.pictureEpisode)
        val details = itemView.findViewById<RelativeLayout>(R.id.btnepisodeDetail)



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