package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.example.misa.iwatch.api.WebServiceFactory
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
class Personnes(
        @SerializedName("id")
        var id:Int,
        @SerializedName("name")
        val nom: String,
        @SerializedName("birthday")
        val dateNaissance: String,
        @SerializedName("place_of_birth")
        val LieuNiassance: String,
        image: String,
        @SerializedName("biography")
        val bibliographie : String,
        @SerializedName("popularity")
        var eval: Float,
        val comments:ArrayList<Comments>,

        val filmographie:ArrayList<associate_Movie>
        ): Serializable
{
    @SerializedName("profile_path")
    val image:String =  image
        get() = WebServiceFactory.IMAGE_BASE_URL+field
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