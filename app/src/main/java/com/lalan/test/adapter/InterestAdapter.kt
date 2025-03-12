package com.lalan.test.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lalan.test.R
import com.lalan.test.model.Interest

class InterestAdapter(val interestList: List<Interest>) :
    RecyclerView.Adapter<InterestAdapter.ViewHolder>() {

    var parentContext: Context? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val interestLayout: LinearLayout = itemView.findViewById(R.id.interestLayout)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleView: TextView = itemView.findViewById(R.id.titleView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        parentContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_interest_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val interest = interestList[holder.adapterPosition]

        holder.imageView.setImageDrawable(interest.drawable)
        holder.titleView.setText(interest.title)

        if (interest.isSelected) {
            holder.interestLayout.setBackgroundResource(
                R.drawable.interest_selected
            )
            holder.imageView.setColorFilter(
                ContextCompat.getColor(
                    parentContext!!,
                    R.color.white
                ), PorterDuff.Mode.SRC_IN
            )
            holder.titleView.setTextColor(
                ContextCompat.getColor(
                    parentContext!!,
                    R.color.white
                )
            )
        } else {
            holder.interestLayout.setBackgroundResource(
                R.drawable.interest_unselected
            )
            holder.imageView.setColorFilter(
                ContextCompat.getColor(
                    parentContext!!,
                    R.color.black
                ), PorterDuff.Mode.SRC_IN
            )
            holder.titleView.setTextColor(
                ContextCompat.getColor(
                    parentContext!!,
                    R.color.black
                )
            )
        }

        holder.interestLayout.setOnClickListener {
            interestList[holder.adapterPosition].isSelected =
                interestList[holder.adapterPosition].isSelected.not()
            notifyItemChanged(holder.adapterPosition)
        }
    }

    override fun getItemCount() = interestList.size
}