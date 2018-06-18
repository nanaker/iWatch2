package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class associate_Movie(

        @SerializedName("id")
        var id:Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("poster_path")
        var image: String
        ,
@SerializedName("overview")
var info : String,
@SerializedName("vote_average")
var voteAverage: Float


) : Serializable
