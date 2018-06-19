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
import com.example.misa.iwatch.ui.activities.PersonDetailActivity
import com.example.misa.iwatch.entity.Personnes

class PersonnesAdapter(val actorList: ArrayList<Personnes>): RecyclerView.Adapter<PersonnesAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.nom?.text = actorList[position].nom
        holder?.dateB?.text = actorList[position].dateNaissance
        holder?.placeB?.text = actorList[position].LieuNiassance
      //  holder?.image?.setImageResource(actorList[position].image)
        holder?.grade?.text = actorList[position].eval.toString()


       holder?.details!!.setOnClickListener {



           val intent = Intent(context, PersonDetailActivity::class.java)
           val bundle = Bundle()

           bundle.putSerializable("personne", actorList[position])
           intent.putExtras(bundle)

           context!!.startActivity(intent)
       }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.persons_item, parent, false)
        context=v.context
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return actorList.size
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