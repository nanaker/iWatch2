package com.example.misa.iwatch.ui.adapters

/**
 * Created by NAWAL on 28/03/2018.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.misa.iwatch.R
import com.example.misa.iwatch.entity.Diffusion

class DiffusionAdapter(val diffusionList: ArrayList<Diffusion>): RecyclerView.Adapter<DiffusionAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.titre?.text = diffusionList[position].nom

        holder?.image?.setImageResource(diffusionList[position].image)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.diffusion_item, parent, false)
        context=v.context
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return diffusionList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val titre = itemView.findViewById<TextView>(R.id.DiffusionName)
        val image = itemView.findViewById<ImageView>(R.id.DiffusionImage)


    }
}