package com.example.misa.iwatch.room.filmdb.modal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.misa.iwatch.entity.*

/**
 * Created by misa on 6/19/18.
 */

@Entity(tableName = "filmData")
data class film(@PrimaryKey(autoGenerate = true) var id: Int,
                @ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "info") var info: String,
                @ColumnInfo(name = "release_date") var release_date: String,
                //@ColumnInfo(name = "associatefilm") var associatefilm: ArrayList<associate_Movie>?,
                //@ColumnInfo(name = "actors") var actors: ArrayList<associate_Actors>?,
                //@ColumnInfo(name = "room") var room: ArrayList<Room>?,
                //@ColumnInfo(name = "comments") var comments: ArrayList<Comments>?,
                //@ColumnInfo(name = "realisateur") var realisateur: Personnes?,
                @ColumnInfo(name = "video") var video: String,
                @ColumnInfo(name = "voteAverage") var voteAverage: Float,
                @ColumnInfo(name = "image") var image: String


){
    constructor():this(0,"", "" , "",  "", 0.0F,"")
}