package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Personnes(
                     @SerializedName("name")
                     val nom: String,
                     val dateNaissance: String,
                     val LieuNiassance: String,
                     var image: Int,
                     val bibliographie : String ,
                     val comments:ArrayList<Comments>,
                     val filmographie:ArrayList<Film> ,
                     var eval: ArrayList<Float>,
                     var image2: Int ): Serializable
{






}