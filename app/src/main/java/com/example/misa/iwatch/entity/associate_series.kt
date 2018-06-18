package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName

data class associate_series(

        @SerializedName("name")
        val nom: String,
        @SerializedName("profile_path")
        var image: String,
        @SerializedName("id")
        var id:Int,
        @SerializedName("overview")
        var info : String,
        @SerializedName("vote_average")
        var voteAverage: Float

) {
}