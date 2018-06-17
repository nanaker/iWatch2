package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Movie(
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        var info: String,
        var directeur: String,
        var image: Int,
        var eval: ArrayList<Float>,
        var comments : ArrayList<Comments>,
        var storyline : String,
        var actors: ArrayList<Personnes>,
        var realisateur :Personnes,
        var room :ArrayList<Room>,
        var associatefilm : ArrayList<Film>,
        var video:String, var fav:Boolean): Serializable{


        companion object {
           val DIFF_CALL = object : DiffUtil.ItemCallback<Movie>(){
                   override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                         return oldItem.title.equals(newItem?.title,true)
                   }

                   override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                           return oldItem.title.equals(newItem?.title,true)
                   }
           }
        }
}
