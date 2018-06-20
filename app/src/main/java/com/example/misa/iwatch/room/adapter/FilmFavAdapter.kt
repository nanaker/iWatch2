package com.example.misa.iwatch.room.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.misa.iwatch.R
import com.example.misa.iwatch.room.filmdb.modal.film


/**
 * Created by misa on 6/19/18.
 */

class FilmFavAdapter(private val context: Context) : RecyclerView.Adapter<FilmFavAdapter.BeanHolder>() {
    private val layoutInflater: LayoutInflater
    private val onNoteItemClick: OnNoteItemClick
    lateinit var list:List<film>

    init {
        layoutInflater = LayoutInflater.from(context)
        this.onNoteItemClick = context as OnNoteItemClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeanHolder {
        val view = layoutInflater.inflate(R.layout.movie_item, parent, false)
        return BeanHolder(view)
    }

    override fun onBindViewHolder(holder: BeanHolder, position: Int) {
        Log.e("bind", "onBindViewHolder: " + list[position])
        holder.textViewTitle.setText(list[position].title)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class BeanHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        internal var textViewTitle: TextView

        init {
            itemView.setOnClickListener(this)
            textViewTitle = itemView.findViewById(R.id.title)
        }

        override fun onClick(view: View) {
            onNoteItemClick.onNoteClick(adapterPosition)
        }
    }

    interface OnNoteItemClick {
        fun onNoteClick(pos: Int)
    }
}