package com.example.misa.iwatch.Repository


interface IRepository{

  enum class Type{
     ACTORS,
     MOVIE,
     SERIES,
      DETAILMOVIE,
      DETAILSERIE,
      DETAILPERSONNE,
      DETAILSAISON,
      DETAILEPISODE
 }
}