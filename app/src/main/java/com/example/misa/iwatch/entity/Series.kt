package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.example.misa.iwatch.api.WebServiceFactory
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
class Series(
        @SerializedName("id")
        var id:Int,
        @SerializedName("name")
        val titre: String,
        @SerializedName("first_air_date")
        val date: String,
        @SerializedName("genre_ids")
        var genres_id : ArrayList<Int>,
        var comments : ArrayList<Comments>,

        var seriesliees: ArrayList<associate_series>,
        @SerializedName("seasons")
        val saisons: ArrayList<Saisons> ,
        @SerializedName("genres")
        var genres:ArrayList<Genre>,
        var video:String,
        var fav:Boolean,
        @SerializedName("overview")
        val info: String,
        @SerializedName("number_of_episodes")
        val nbEposides: String,
        @SerializedName("number_of_seasons")
        val nbSaisons: String,
        image: String,
        @SerializedName("vote_average")
        var voteAverage: Float
                  ): Serializable {
    @SerializedName("poster_path")
    val image:String =  image
        get() = WebServiceFactory.IMAGE_BASE_URL+field

    companion object {
        val DIFF_CALL = object : DiffUtil.ItemCallback<Series>() {
            override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem.titre.equals(newItem?.titre, true)
            }

            override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem.titre.equals(newItem?.titre, true)
            }
        }
    }
}



