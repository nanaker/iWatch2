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
import com.bumptech.glide.Glide
import com.example.misa.iwatch.R
import com.example.misa.iwatch.api.WebServiceFactory.Companion.IMAGE_BASE_URL
import com.example.misa.iwatch.ui.activities.PersonDetailActivity
import com.example.misa.iwatch.entity.Personnes
import com.example.misa.iwatch.entity.associate_Actors

class ActorsDetailAdapter(val actorList: ArrayList<associate_Actors>): RecyclerView.Adapter<ActorsDetailAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.nom?.text = actorList[position].nom
        Glide.with(this!!.context!!)
                .load(IMAGE_BASE_URL+actorList[position].image)
                .into(holder?.image)
      //  holder?.image?.setImageResource(actorList[position].image)
        holder?.details?.setOnClickListener {

            val intent = Intent(context, PersonDetailActivity::class.java)
            val bundle = Bundle()

            bundle.putInt("id_personne", actorList[position].id)
            intent.putExtras(bundle)

            context!!.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.actors_detail_item, parent, false)
        context=v.context
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nom = itemView.findViewById<TextView>(R.id.name_actor_detail)
        val image = itemView.findViewById<ImageView>(R.id.picture_actor_detail)
        val details = itemView.findViewById<LinearLayout>(R.id.btnDetailactor)



    }
}