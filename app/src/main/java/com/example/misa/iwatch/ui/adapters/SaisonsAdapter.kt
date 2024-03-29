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
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.misa.iwatch.R
import com.example.misa.iwatch.api.WebServiceFactory
import com.example.misa.iwatch.ui.activities.SaisonsDetailActivity
import com.example.misa.iwatch.entity.Saisons

class SaisonsAdapter(val saisonList: ArrayList<Saisons>,val id : Int): RecyclerView.Adapter<SaisonsAdapter.ViewHolder>() {
    private var context: Context? = null


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.titre?.text = saisonList[position].titre
        holder?.info?.text = saisonList[position].nbEpisodes.toString() +" Episodes"
        holder?.date?.text= saisonList[position]?.date
        Glide.with(this!!.context!!)
                .load(WebServiceFactory.IMAGE_BASE_URL +saisonList[position]?.image)
                .into(holder?.image)
      //  holder?.image?.setImageResource(saisonList[position].image)



       holder?.details!!.setOnClickListener {
           val intent = Intent(context, SaisonsDetailActivity::class.java)
           val bundle = Bundle()
           bundle.putInt("id_saison", position)
           bundle.putInt("id_serie", this.id)
           intent.putExtras(bundle)

           context!!.startActivity(intent)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.saisons_item, parent, false)
        context=v.context
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return saisonList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val titre = itemView.findViewById<TextView>(R.id.titleSaisons)
        val info = itemView.findViewById<TextView>(R.id.nbEpisodesSaison)
        val date= itemView.findViewById<TextView>(R.id.datesortiesaison)
        val image = itemView.findViewById<ImageView>(R.id.picturePersonneSaisons)
        val details = itemView.findViewById<RelativeLayout>(R.id.btnsaisonDetail)


    }
}