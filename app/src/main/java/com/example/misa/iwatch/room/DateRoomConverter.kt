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
    fun stringToAssociateMovie(data: String?): ArrayList<associate_Movie> {
        if (data == null) {
            return EMPTY_LIST as ArrayList<associate_Movie>
        }

        val listType = object : TypeToken<ArrayList<associate_Movie>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToAssociateMovie(someObjects: ArrayList<associate_Movie>): String {
        return gson.toJson(someObjects)
    }

}