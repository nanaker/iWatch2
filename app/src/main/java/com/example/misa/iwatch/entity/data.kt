package com.example.misa.iwatch.entity
import com.google.android.gms.maps.model.LatLng


import com.example.misa.iwatch.R

/**
 * Created by NAWAL on 02/04/2018.
 */
var PARAMS_NAME = "PARAMS"
class data {


    companion object {
        var Filmsfav = ArrayList<Movie>()
        var Films = ArrayList<Movie>()
        var Series = ArrayList<Series>()
        var SeriesFav = ArrayList<Series>()
        var Acteurs = ArrayList<Personnes>()
        var Realisateurs = ArrayList<Personnes>()
        var Cinemas = ArrayList<Cinema>()
        var CinemasFav = ArrayList<Cinema>()




    fun getMoviesRecent(): ArrayList<Movie> {

        return ArrayList()

    }

        fun getSeriesRecent(): ArrayList<Series> {

            return ArrayList()

        }
        fun getRealisators(): ArrayList<Personnes> {

            return ArrayList()

        }
           fun getActors(): ArrayList<Personnes> {

              return ArrayList()

          }


        fun getCinema(): ArrayList<Cinema> {


            if (Cinemas.size<1) {
                val arg = LatLng(40.7340685, -73.9933289)
                val arg1 = LatLng(48.8682668,2.366204)
                val arg2 = LatLng(40.7359436, -73.9960851)

                Cinemas.add(Cinema("Cinema Village ", "22 E 12th St, New York, NY 10003, États-Unis ", R.drawable.cinema_room,arg,false))
                Cinemas.add(Cinema("Apollo Théâtre ", "18 Rue du Faubourg du Temple, 75011 Paris, France", R.drawable.apollo,arg1,false))
                Cinemas.add(Cinema("Quad Cinema ", "34 W 13th St, New York, NY 10011, États-Unis ", R.drawable.quad,arg2,false))
            }
            return Cinemas
        }
        
        fun getMoviesFav():ArrayList<Movie> {

            return Filmsfav;
        }
        fun getSerieFav():ArrayList<Series> {

            return SeriesFav;
        }
        fun getCinemaFav():ArrayList<Cinema> {

            return CinemasFav;
        }
    }





}