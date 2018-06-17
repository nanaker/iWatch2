package com.example.misa.iwatch.entity

import com.google.android.gms.maps.model.LatLng

import java.io.Serializable

/**
 * Created by NAWAL on 28/03/2018.
 */
data class Cinema(val name: String, val address: String, var image: Int, var langlat:LatLng, var fav:Boolean) : Serializable
{



}