package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Personnes(
        @SerializedName("id")
        var id:Int,
        @SerializedName("name")
        val nom: String,
        @SerializedName("birthday")
        val dateNaissance: String,
        @SerializedName("place_of_birth")
        val LieuNiassance: String,
        @SerializedName("profile_path")
        var image: String,
        @SerializedName("biography")
        val bibliographie : String,
        @SerializedName("popularity")
        var eval: Float,
        val comments:ArrayList<Comments>,

        val filmographie:ArrayList<associate_Movie>,

        var image2: Int ): Serializable
{







}