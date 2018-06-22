package com.example.misa.iwatch.ui.activities

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.evernote.android.job.JobManager
import com.example.misa.iwatch.R
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.entity.Genre
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.notification.LatestMovieJob
import com.example.misa.iwatch.notification.LatestMovieJobCreator
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.ui.fragments.*
import com.example.misa.iwatch.utils.ServiceLocator

import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){



    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter
        container.currentItem = 0

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))


        JobManager.create(this).addJobCreator(LatestMovieJobCreator())
        LatestMovieJob .schedulePeriodicJob();
        //test de notification
       // traiter_job()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
     class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            Log.d("TAB++++++++","position ${position}")
           return when (position){
                0 -> HomeFragment()
                1 -> CinemaFragment()
                2 -> SeriesFragment()
                3 -> PersonsFragment()
                4 -> FunFragment()
                else -> null
            }
        }

        override fun getCount(): Int {
            // Show 5 total pages.
            return 5
        }

    }


    //test notif
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





                 val Intent = Intent(this , MovieDetailActivity::class.java)
                 Intent.putExtra("id_movie", movie.id)
                 val pendingIntent = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), Intent, 0)

                 notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                 if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                     val mChannel = NotificationChannel(
                             channelId, channelName, importance)
                     notificationManager.createNotificationChannel(mChannel)
                 }

                 if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                     myNotification = NotificationCompat.Builder(this,channelId)
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

                     myNotification = NotificationCompat.Builder(this,channelId)
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
