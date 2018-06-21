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
import com.example.misa.iwatch.ui.activities.PersonDetailActivity
import com.example.misa.iwatch.entity.Personnes

class PersonnesAdapter(val context: Context): PagedListAdapter<Personnes,PersonnesAdapter.ViewHolder>(Personnes.DIFF_CALL) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("TAG",getItem(position).toString())
        holder?.nom?.text = getItem(position)?.nom
        holder?.dateB?.text = getItem(position)?.dateNaissance
        holder?.placeB?.text = getItem(position)?.LieuNiassance
        Log.d("TAG",getItem(position)?.image)
        Glide.with(context)
                .load(getItem(position)?.image)
                .into(holder?.image)
        holder?.grade.text = getItem(position)?.eval.toString().substring(0,2)



       holder?.details!!.setOnClickListener {



           val intent = Intent(context, PersonDetailActivity::class.java)
           val bundle = Bundle()

           bundle.putSerializable("id_personne", getItem(position)?.id)
           intent.putExtras(bundle)

           context!!.startActivity(intent)
       }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.persons_item, parent, false)
        return ViewHolder(v);
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val nom = itemView.findViewById<TextView>(R.id.Nom)
        val dateB = itemView.findViewById<TextView>(R.id.dateB)
        val placeB = itemView.findViewById<TextView>(R.id.PlaceB)
        val image = itemView.findViewById<ImageView>(R.id.pictureMovieDetail)
        val details = itemView.findViewById<RelativeLayout>(R.id.btnPersonneDetail)
        val grade= itemView.findViewById<Button>(R.id.grade_personne)


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