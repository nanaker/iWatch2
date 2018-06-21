package com.example.misa.iwatch.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class associate_Actors(
                            @SerializedName("name")
                            val nom: String,
                            @SerializedName("profile_path")
                            var image: String,
                            @SerializedName("id")
                            var id:Int


                            ): Serializable
{






}