package com.example.misa.iwatch.room.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.misa.iwatch.R
import com.example.misa.iwatch.api.WebServiceFactory
import com.example.misa.iwatch.room.filmdb.modal.film
import com.example.misa.iwatch.ui.activities.MovieDetailActivity


/**
 * Created by misa on 6/19/18.
 */

class FilmFavAdapter(private var list:List<film>, private val context: Context) : RecyclerView.Adapter<FilmFavAdapter.BeanHolder>() {
    private var layoutInflater: LayoutInflater
    //private val onNoteItemClick: OnNoteItemClick

    init {
        layoutInflater = LayoutInflater.from(context)
//        this.onNoteItemClick = context as OnNoteItemClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeanHolder {
        val view = layoutInflater.inflate(R.layout.movie_item, parent, false)
        return BeanHolder(view)
    }

    override fun onBindViewHolder(holder: BeanHolder, position: Int) {
        Log.e("bind", "onBindViewHolder: " + list[position])
        holder.textViewTitle.setText(list[position].title)
        holder.textViewInfo.setText(list[position].info.take(50)+"...")
        holder.textViewDate.setText(list[position].release_date)
        holder.textViewVote.setText(list[position].voteAverage.toString().substring(0,3))

        val photoPath = Environment.getExternalStorageDirectory().toString() + "/"+list[position].id.toString()+".jpg"
        val bitmap = BitmapFactory.decodeFile(photoPath)
        holder.imageView.setImageBitmap(bitmap)
        holder.layout.setOnClickListener(){
            val intent = Intent(context, MovieDetailActivity::class.java)
            val bundle = Bundle()
            // bundle.putSerializable("film", getItem(position))

            bundle.putInt("id_movie", list[position]!!.id)
            bundle.putString("tag", WebServiceFactory.TAG_BDD)
            bundle.putSerializable("film",list[position])
            intent.putExtras(bundle)
            context!!.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class BeanHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        internal var textViewTitle: TextView
        internal var textViewInfo: TextView
        internal var textViewDate: TextView
        internal var textViewVote: TextView
        internal var imageView: ImageView
        internal var layout: RelativeLayout

        init {
            itemView.setOnClickListener(this)
            textViewTitle = itemView.findViewById(R.id.title)
            textViewInfo = itemView.findViewById(R.id.details)
            textViewDate = itemView.findViewById(R.id.directorName)
            textViewVote = itemView.findViewById(R.id.grade)
            imageView = itemView.findViewById(R.id.pictureMovieDetail)
            imageView = itemView.findViewById(R.id.pictureMovieDetail)
            layout=itemView.findViewById(R.id.btnMovieDetail)

        }

        override fun onClick(view: View) {
            //onNoteItemClick.onNoteClick(adapterPosition)
        }
    }

}