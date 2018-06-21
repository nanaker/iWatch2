package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Personnes(
<<<<<<< HEAD
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
=======
        @SerializedName("id")
        var id:Int,
        @SerializedName("name")
        val nom: String,
        @SerializedName("birthday")
        val dateNaissance: String,
        @SerializedName("place_of_birth")
        val LieuNiassance: String,
        @SerializedName("profile_path")
        var image: String,
        @SerializedName("biography")
        val bibliographie : String,
        @SerializedName("popularity")
        var eval: Float,
        val comments:ArrayList<Comments>,

        val filmographie:ArrayList<associate_Movie>,

        var image2: Int ): Serializable
>>>>>>> 4692160fe9af67cad22d68a86b37f72256743fff
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