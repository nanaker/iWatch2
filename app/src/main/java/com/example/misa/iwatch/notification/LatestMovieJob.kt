package com.example.misa.iwatch.notification

import android.app.Notification
import android.app.NotificationChannel
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.evernote.android.job.Job
import com.evernote.android.job.JobRequest
import javax.xml.datatype.DatatypeConstants.MINUTES
import java.util.concurrent.TimeUnit
import com.evernote.android.job.JobManager
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.entity.Genre
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import com.example.misa.iwatch.R.mipmap.ic_launcher
import android.content.Intent
import android.app.PendingIntent
import android.content.Context
import android.support.v4.app.NotificationCompat
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.activities.MovieDetailActivity





class LatestMovieJob : Job() {
    companion object {
        val TAG = "job_latest_movie"



        fun schedulePeriodicJob() {
            val jobRequests = JobManager.instance().getAllJobRequestsForTag(LatestMovieJob.TAG)
            if (!jobRequests.isEmpty()) {
                return
            }
            JobRequest.Builder(LatestMovieJob.TAG)
                    .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                    .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                    .build()
                    .schedule()
        }


    }




    override fun onRunJob(params: Params): Result {

        // run your job here
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        println("job running at "+ currentDate)
        traiter_job()

        return Result.SUCCESS;
    }

    private fun traiter_job() {
        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILMOVIE) as MovieDetailRepository

        val DetailFilmModel =  MoviesDetailViewModel(repo)
        DetailFilmModel.getLatestMovie()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)

    }


    private fun handleResponse(movie: Movie) {
        val notificationManager: NotificationManager
        val myNotification: Notification

         val channelId = "channel latest movie"
         val channelName = "movie Channel"
         val importance = NotificationManager.IMPORTANCE_HIGH

        val genres:ArrayList<Genre> = get_Genre();
        val id_movie=getIdLatestMovie()
        if(movie.id!=id_movie){
            if (genre_correspond(movie.genres,genres)){





                val Intent = Intent(context, MovieDetailActivity::class.java)
                Intent.putExtra("id_movie", movie.id)
                val pendingIntent = PendingIntent.getActivity(context, System.currentTimeMillis().toInt(), Intent, 0)

                 notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    val mChannel = NotificationChannel(
                            channelId, channelName, importance)
                    notificationManager.createNotificationChannel(mChannel)
                }
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    myNotification = NotificationCompat.Builder(context,channelId)
                            .setContentTitle("Un nouveau Film est sorti")
                            .setContentText("Titre : "+movie.title)
                            .setTicker("Nouveau Film")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.transp)
                            .build()

                } else {

                    myNotification = NotificationCompat.Builder(context,channelId)
                            .setContentTitle("Un nouveau Film est sorti")
                            .setContentText("Titre : "+movie.title)
                            .setTicker("Nouveau Film")
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.app)
                            .build()
                }

                notificationManager.notify(0, myNotification)



            }
        }
    }

    private fun genre_correspond(genres: ArrayList<Genre>, genres1: ArrayList<Genre>): Boolean {
        var correspond:Boolean=false;
        loop@for (item in genres){
            for (item2 in genres1){
                if (item.id==item2.id){
                    correspond=true;
                    break@loop

                }
            }
        }
        return true;

    }

    private fun getIdLatestMovie(): Int {
        return 15;

    }

    private fun get_Genre(): ArrayList<Genre> {
        val genre=ArrayList<Genre>()
        genre.add(Genre(28,"Action"))
        genre.add(Genre(12,"Adventure"))
        genre.add(Genre(35,"Comedy"))
        genre.add(Genre(80,"Crime"))
        genre.add(Genre(99,"Documentary"))
        genre.add(Genre(18,"Drama"))
        genre.add(Genre(10751,"Family"))
        genre.add(Genre(14,"Fantasy"))
        genre.add(Genre(36,"History"))
        genre.add(Genre(27,"Horror"))
        genre.add(Genre(10402,"Music"))
        genre.add(Genre(9648,"Mystery"))
        genre.add(Genre(10749,"Romance"))
        genre.add(Genre(878,"Science Fiction"))
        genre.add(Genre(10770,"TV Movie"))
        genre.add(Genre(53,"Thriller"))
        genre.add(Genre(10752,"War"))
        genre.add(Genre(37,"Western"))


        return genre

    }

    private fun handleError(error: Throwable) { Log.d("error movie load ", error.localizedMessage) }




}
