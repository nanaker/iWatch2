package com.example.misa.iwatch.ui.adapters

/**
 * Created by NAWAL on 28/03/2018.
 */

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.misa.iwatch.ui.activities.MapsActivity
import com.example.misa.iwatch.R
import com.example.misa.iwatch.entity.Cinema
import com.example.misa.iwatch.entity.data
import com.github.ivbaranov.mfb.MaterialFavoriteButton

class CinemaAdapter(val cinemaList: ArrayList<Cinema>): RecyclerView.Adapter<CinemaAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.titre?.text = cinemaList[position].name
        holder?.adress?.text = cinemaList[position].address
        holder?.image?.setImageResource(cinemaList[position].image)
        if (cinemaList[position].fav) {
            holder?.fav?.isFavorite = true
        }
        holder?.fav?.setOnClickListener{

            cinemaList[position].fav = true
            data.CinemasFav.add(cinemaList[position])
             holder?.fav?.isFavorite = true

        }

        holder?.map?.setOnClickListener {

            val intent = Intent(context, MapsActivity::class.java)

            //val arg = LatLng(40.7340685, -73.9933289)

            //intent.putExtra("Latlng", arg)
           intent.putExtra("Latlng", cinemaList[position].langlat)
           intent.putExtra("nom_cin", cinemaList[position].name)

            context!!.startActivity(intent)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.cinema_room_item, parent, false)
        context=v.context
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return cinemaList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titre = itemView.findViewById<TextView>(R.id.nameCinema)
        val adress = itemView.findViewById<TextView>(R.id.Adress)
        val image = itemView.findViewById<ImageView>(R.id.pictureCinema)
        val fav = itemView.findViewById<MaterialFavoriteButton>(R.id.cinemaFavori)
        val map = itemView.findViewById<CardView>(R.id.cardViewRoom)

    }
}