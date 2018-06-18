package com.example.misa.iwatch.entity

import android.support.v7.util.DiffUtil
import com.example.misa.iwatch.api.WebServiceFactory
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
class Series(
                  @SerializedName("name")
                  val titre: String,
                  @SerializedName("overview")
                  val info: String,
                  val directeur: String,
                  image: String,
                  @SerializedName("vote_average")
                  var voteAverage: Float,
                  var comments : ArrayList<Comments>,
                  var storyline : String ,
                  var seriesliees: ArrayList<Int>,
                  val saisons: ArrayList<Saisons> ,
                  var video:String,var fav:Boolean): Serializable {
    @SerializedName("poster_path")
    val image:String =  image
        get() = WebServiceFactory.IMAGE_BASE_URL+field

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



