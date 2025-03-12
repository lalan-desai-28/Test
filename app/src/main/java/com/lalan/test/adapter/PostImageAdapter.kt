package com.lalan.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lalan.test.R
import com.lalan.test.model.dashboard.Media

class PostImageAdapter(private val imageList: List<Media>) :
    RecyclerView.Adapter<PostImageAdapter.ViewHolder>() {

    var parentContext: Context? = null

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postImageView: ImageView = itemView.findViewById(R.id.postImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        parentContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postImage = imageList[holder.adapterPosition]
        Glide.with(parentContext!!)
            .load(postImage.path)
            .fitCenter()
            .into(holder.postImageView)
    }

}