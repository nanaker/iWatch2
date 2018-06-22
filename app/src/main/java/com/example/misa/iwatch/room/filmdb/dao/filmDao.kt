package com.example.misa.iwatch.room.filmdb.dao

import android.arch.persistence.room.*
import com.example.misa.iwatch.room.filmdb.modal.film

/**
 * Created by misa on 6/19/18.
 */

@Dao
interface filmDao {

    @Query("SELECT * from filmData")
    fun getFilmFav(): List<film>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addFilmFav(film: film): Long

    @Update
    fun updateFilm(film: film)

    @Delete
    fun deleteFilmFav(film: film)
}