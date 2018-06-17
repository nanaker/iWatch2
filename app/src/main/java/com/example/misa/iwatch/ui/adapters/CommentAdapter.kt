package com.example.misa.iwatch.ui.adapters

/**
 * Created by NAWAL on 28/03/2018.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.misa.iwatch.R
import com.example.misa.iwatch.entity.Comments


class CommentAdapter(val commentList: ArrayList<Comments>): RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    private var context: Context? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.user?.text = commentList[position].user
        holder?.comment?.text = commentList[position].comment
        holder?.image?.setImageResource(commentList[position].image)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.comment_item, parent, false)
        context=v.context
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val user = itemView.findViewById<TextView>(R.id.userName)
        val comment = itemView.findViewById<TextView>(R.id.comment)
        val image = itemView.findViewById<ImageView>(R.id.userAvatar)



    }
}