package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Episode(
        @SerializedName("id")
        val id:Int,
        @SerializedName("name")
        val nom: String,
        @SerializedName("air_date")
        val date: String,
        @SerializedName("still_path")
        var image: String,
        @SerializedName("episode_number")
        var nb_episode: Int,
        @SerializedName("overview")
        var storyline : String ,
        @SerializedName("vote_average")
        var eval: Float,
        var video:String ): Serializable
{






}