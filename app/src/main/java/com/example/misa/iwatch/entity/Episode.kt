package com.example.misa.iwatch.entity

import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Episode(val nom: String,  val duree: String, var image: Int, var storyline : String ,
                   var diffusion: ArrayList<Diffusion> ,var comments : ArrayList<Comments> ,
                   var eval: ArrayList<Float>, var video:String ): Serializable
{






}