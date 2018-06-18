package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.example.misa.iwatch.api.WebServiceFactory
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
 class Movie(
        @SerializedName("id")
        var id:Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("overview")
        var info : String,
        @SerializedName("genre_ids")
        var genres_id : ArrayList<Int>,
        @SerializedName("release_date")
        var release_date:String,
        //Detail movies
        @SerializedName("genres")
        var genres:ArrayList<Genre>,
        var associatefilm : ArrayList<associate_Movie>,
        var actors: ArrayList<associate_Actors>,
        var room :ArrayList<Room>,
        var comments : ArrayList<Comments>,


        var realisateur :Personnes,


        var video:String,
        var fav:Boolean,
        image: String,
        @SerializedName("vote_average")
        var voteAverage: Float
           ): Serializable{
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
