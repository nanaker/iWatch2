package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName

data class associate_series(

        @SerializedName("name")
        val nom: String,
        @SerializedName("backdrop_path")
        var image: String,
        @SerializedName("id")
        var id:Int,
        @SerializedName("overview")
        var info : String,
        @SerializedName("vote_average")
        var voteAverage: Float,
        @SerializedName("first_air_date")
         var date: String

) {
}