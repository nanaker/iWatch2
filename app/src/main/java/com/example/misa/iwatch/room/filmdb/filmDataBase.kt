package com.example.misa.iwatch.room.filmdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.misa.iwatch.room.filmdb.dao.filmDao
import com.example.misa.iwatch.room.filmdb.modal.film

/**
 * Created by misa on 6/19/18.
 */

@Database(entities = arrayOf(film::class), version = 1)
abstract class filmDataBase : RoomDatabase() {

    abstract fun getFilmDao(): filmDao


    companion object {
        private var INSTANCE: filmDataBase? = null

        fun getInstance(context: Context): filmDataBase? {
            if (INSTANCE == null) {
                synchronized(filmDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            filmDataBase::class.java, "weather.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}