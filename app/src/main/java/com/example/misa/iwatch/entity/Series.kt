package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Series(
                  @SerializedName("name")
                  val titre: String,
                  @SerializedName("overview")
                  val info: String,
                  val directeur: String,
                  var image: Int ,
                  var eval: ArrayList<Float>,
                  var comments : ArrayList<Comments>,
                  var storyline : String ,
                  var seriesliees: ArrayList<Int>,
                  val saisons: ArrayList<Saisons> ,
                  var video:String,var fav:Boolean): Serializable {

    companion object {
        val DIFF_CALL = object : DiffUtil.ItemCallback<Series>() {
            override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem.titre.equals(newItem?.titre, true)
            }

            override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem.titre.equals(newItem?.titre, true)
            }
        }
    }
}



