package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.example.misa.iwatch.api.WebServiceFactory
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
 class Movie(
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        var info: String,
        var directeur: String,
        image: String,
        @SerializedName("vote_average")
        var voteAverage: Float,
        var comments : ArrayList<Comments>,
        var storyline : String,
        var actors: ArrayList<Personnes>,
        var realisateur :Personnes,
        var room :ArrayList<Room>,
        var associatefilm : ArrayList<Film>,
        var video:String,
        var fav:Boolean): Serializable{
        @SerializedName("poster_path")
        val image:String =  image
            get() = WebServiceFactory.IMAGE_BASE_URL+field
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
