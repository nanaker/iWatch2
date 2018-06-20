package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Personnes(
                     val id:Int,
                     @SerializedName("name")
                     val nom: String,
                     @SerializedName("birthday")
                     val dateNaissance: String,
                     val LieuNiassance: String,
                     var image: Int,
                     val bibliographie : String ,
                     val comments:ArrayList<Comments>,
                     val filmographie:ArrayList<Film> ,
                     var eval: ArrayList<Float>,
                     var image2: Int ): Serializable
{
    companion object {
        val DIFF_CALL = object : DiffUtil.ItemCallback<Personnes>(){
            override fun areItemsTheSame(oldItem: Personnes, newItem: Personnes): Boolean {
                return oldItem.nom.equals(newItem?.nom,true)
            }

            override fun areContentsTheSame(oldItem: Personnes, newItem: Personnes): Boolean {
                return oldItem.nom.equals(newItem?.nom,true)
            }
        }
    }





}