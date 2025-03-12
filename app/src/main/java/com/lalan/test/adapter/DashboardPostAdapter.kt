package com.lalan.test.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lalan.test.MyApplication
import com.lalan.test.R
import com.lalan.test.model.dashboard.Data
import com.lalan.test.viewmodel.DashboardDataViewModel


class DashboardPostAdapter(
    val postList: List<Data>,
    val dashboardDataViewModel: DashboardDataViewModel
) :
    RecyclerView.Adapter<DashboardPostAdapter.ViewHolder>() {

    var parentContext: Context? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImageView: ImageView = itemView.findViewById(R.id.userImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val postTimeTextView: TextView = itemView.findViewById(R.id.postTimeTextView)
        val postImagesPageViewPager: ViewPager2 =
            itemView.findViewById(R.id.postImagesPageViewPager)

        val likeProgressBar: ProgressBar = itemView.findViewById(R.id.likeProgressBar)
        val likeUnlikeImageButton: ImageButton = itemView.findViewById(R.id.likeUnlikeImageButton)
        val saveUnsaveImageButton: ImageButton = itemView.findViewById(R.id.saveUnsaveImageButton)
        val likeCounterTextView: TextView = itemView.findViewById(R.id.likeCounterTextView)
        val commentCounterTextView: TextView = itemView.findViewById(R.id.commentCounterTextView)

        val imageTabLayout: TabLayout = itemView.findViewById(R.id.imageTabLayout)

        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        parentContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = postList[holder.adapterPosition]

        Glide.with(parentContext!!).load(data.profilePhoto)
            .centerCrop()
            .into(holder.userImageView)

        holder.nameTextView.setText(data.name)
        holder.postTimeTextView.setText(data.createdAt)

        holder.likeCounterTextView.setText(data.post?.likesCount.toString())
        holder.commentCounterTextView.setText(data.post?.commentsCount.toString())

        holder.descriptionTextView.setText(data.post?.description)

        holder.postImagesPageViewPager.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: YES CLICKED")
        }

        holder.postImagesPageViewPager.adapter =
            PostImageAdapter(data.post?.media?.filter { it.type == "image" } ?: emptyList())

        TabLayoutMediator(holder.imageTabLayout, holder.postImagesPageViewPager, { tab, position ->
            tab.text = ""
        }).attach()

        holder.likeUnlikeImageButton.setImageDrawable(
            if (data.post?.isLiked == 0)
                ContextCompat.getDrawable(parentContext!!, R.drawable.like)
            else
                ContextCompat.getDrawable(parentContext!!, R.drawable.unlike)
        )


        holder.saveUnsaveImageButton.setImageDrawable(
            if (data.post?.isSaved == 0)
                ContextCompat.getDrawable(parentContext!!, R.drawable.save)
            else
                ContextCompat.getDrawable(parentContext!!, R.drawable.unsave)
        )

        holder.likeUnlikeImageButton.setOnClickListener {
            dashboardDataViewModel.likeUnlikeFeed(data.feedId, MyApplication.sessionToken)
        }

    }
}