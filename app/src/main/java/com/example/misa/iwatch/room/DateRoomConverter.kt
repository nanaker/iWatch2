package com.example.misa.iwatch.room

import android.arch.persistence.room.TypeConverter
import com.example.misa.iwatch.entity.*
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.util.*
import java.util.Collections.EMPTY_LIST
import kotlin.collections.ArrayList


/**
 * Created by misa on 6/21/18.
 */
class DateRoomConverter {

    var gson = Gson()

    //convertir la liste des films associés en String
    @TypeConverter
    fun stringToAssociateMovie(data: String?): ArrayList<associate_Movie>? {
        if (data == null) {
            return EMPTY_LIST as ArrayList<associate_Movie>?
        }

        val listType = object : TypeToken<ArrayList<associate_Movie>?>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToAssociateMovie(someObjects: ArrayList<associate_Movie>?): String {
        return gson.toJson(someObjects)
    }


    //convertir la liste des Actors associés en String
    @TypeConverter
    fun stringToAssociateActors(data: String?): ArrayList<associate_Actors>? {
        if (data == null) {
            return EMPTY_LIST as ArrayList<associate_Actors>?
        }

        val listType = object : TypeToken<ArrayList<associate_Actors>?>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToAssociateActors(someObjects: ArrayList<associate_Actors>?): String {
        return gson.toJson(someObjects)
    }

    //convertir la liste des Comments en String
    @TypeConverter
    fun stringToAssociateComments(data: String?): ArrayList<Comments>? {
        if (data == null) {
            return EMPTY_LIST as ArrayList<Comments>?
        }

        val listType = object : TypeToken<ArrayList<Comments>?>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToAssociateComments(someObjects: ArrayList<Comments>?): String {
        return gson.toJson(someObjects)
    }


    //convertir la liste des Genres en String
    @TypeConverter
    fun stringToAssociateGenres(data: String?): ArrayList<Genre>? {
        if (data == null) {
            return EMPTY_LIST as ArrayList<Genre>?
        }

        val listType = object : TypeToken<ArrayList<Genre>?>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToAssociateGenres(someObjects: ArrayList<Genre>?): String {
        return gson.toJson(someObjects)
    }

}