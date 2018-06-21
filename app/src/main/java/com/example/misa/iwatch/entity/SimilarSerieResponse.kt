package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 17/04/2018.
 */
data class SimilarSerieResponse(

        @SerializedName("results")
        val associate_series: ArrayList<associate_series>



): Serializable
{

}