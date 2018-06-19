package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 17/04/2018.
 */
data class CreditsResponse(
        @SerializedName("id")
        var id:Int,
        @SerializedName("cast")
        val associate_Actors: ArrayList<associate_Actors>


): Serializable
{

}