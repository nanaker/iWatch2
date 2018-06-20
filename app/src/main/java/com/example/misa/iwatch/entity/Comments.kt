package com.example.misa.iwatch.entity

import com.example.misa.iwatch.R
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 17/04/2018.
 */
data class Comments(
        @SerializedName("author")
        val user: String,
        @SerializedName("content")
        val comment: String,
        @SerializedName("id")
        var id:String,
        val image: Int= R.drawable.a
): Serializable
{

}