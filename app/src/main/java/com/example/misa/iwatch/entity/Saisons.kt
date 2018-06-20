package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Saisons(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val titre: String,
        val info: String,
        @SerializedName("poster_path")
        var image: String  ,
        var comments : ArrayList<Comments>,
        @SerializedName("overview")
        var storyline : String ,
        @SerializedName("air_date")
        var date:String,
        @SerializedName("episode_count")
        var nbEpisodes:String,
        @SerializedName("season_number")
        var nbsaison:String,
        @SerializedName("episodes")
        var episode: ArrayList<Episode> ,
        var actors: ArrayList<Personnes>,
        var video:String): Serializable
{





}