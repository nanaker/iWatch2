package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 17/04/2018.
 */
data class SimilarMovieResponse(

        @SerializedName("results")
        val associate_Movie: ArrayList<associate_Movie>



): Serializable
{

}