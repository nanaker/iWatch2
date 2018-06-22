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
import com.example.misa.iwatch.api.WebServiceFactory
import com.example.misa.iwatch.api.WebServiceFactory.Companion.IMAGE_BASE_URL
import com.example.misa.iwatch.ui.activities.MovieDetailActivity
import com.example.misa.iwatch.entity.*


class AssociateFilmAdapter(val associateFilmList: ArrayList<associate_Movie>): RecyclerView.Adapter<AssociateFilmAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.name?.text = associateFilmList[position].title
        Glide.with(this!!.context!!)
                .load(IMAGE_BASE_URL+associateFilmList[position].image)
                .into(holder?.image)
       // holder?.image?.setImageResource(associateFilmList[position].image)

        holder?.detail?.setOnClickListener {

            val intent = Intent(context, MovieDetailActivity::class.java)
            val bundle = Bundle()

            bundle.putInt("id_movie", associateFilmList[position].id)
            bundle.putString("tag", WebServiceFactory.TAG_API)
            intent.putExtras(bundle)

            context!!.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.associate_film_item, parent, false)
        context = v.context
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return associateFilmList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.name_associate_film)
        val image = itemView.findViewById<ImageView>(R.id.picture_associate_film)
        val detail= itemView.findViewById<LinearLayout>(R.id.btnDetailfilmassocie)

    }
}