package com.example.misa.iwatch.entity

import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Saisons(val titre: String, val info: String,  var image: Int  , var eval: ArrayList<Float>
                   , var comments : ArrayList<Comments>, var storyline : String ,
                   var episode: ArrayList<Episode> , var actors: ArrayList<Personnes>, var video:String): Serializable
{





}