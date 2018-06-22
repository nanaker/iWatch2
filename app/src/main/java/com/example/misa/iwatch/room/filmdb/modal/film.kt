package com.example.misa.iwatch.room.filmdb.modal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.misa.iwatch.entity.*

/**
 * Created by misa on 6/19/18.
 */

@Entity(tableName = "filmData")
data class film(
                @PrimaryKey(autoGenerate = true) var id: Int,
                @ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "info") var info: String,
                @ColumnInfo(name = "release_date") var release_date: String,
                //@ColumnInfo(name = "associatefilm") var associatefilm: ArrayList<associate_Movie>?,
                //@ColumnInfo(name = "actors") var actors: ArrayList<associate_Actors>?,
                //@ColumnInfo(name = "room") var room: ArrayList<Cinema>?,
                //@ColumnInfo(name = "comments") var comments: ArrayList<Comments>?,
                //@ColumnInfo(name = "realisateur") var realisateur: Personnes?,
                @ColumnInfo(name = "video") var video: String,
                @ColumnInfo(name = "voteAverage") var voteAverage: Float,
                @ColumnInfo(name = "image") var image: String
            ){

    constructor():this(0, "", "", "", "", 0.0F, "")

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is film) return false

        val film = o as film?

        if (id != film!!.id) return false
        return if (title != null) title == film!!.title else film!!.title == null
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + if (title != null) title.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "Film{" +
                "film_id=" + id +
                ", info='" + info + '\''.toString() +
                ", title='" + title + '\''.toString() +
                ", date=" + release_date +
                '}'.toString()
    }
}