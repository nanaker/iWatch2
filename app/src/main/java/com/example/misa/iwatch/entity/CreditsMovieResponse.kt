package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 17/04/2018.
 */
data class CreditsMovieResponse(

        @SerializedName("results")
        val associate_Movie: ArrayList<associate_Movie>,
        @SerializedName("cast")
        val associate_Movie_actors: ArrayList<associate_Movie>



): Serializable
{

}