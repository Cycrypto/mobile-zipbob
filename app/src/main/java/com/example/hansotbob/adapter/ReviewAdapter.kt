package com.example.hansotbob.adapter

import com.example.hansotbob.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hansotbob.dto.Review


class ReviewAdapter(private val reviewList: MutableList<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nickname: TextView = itemView.findViewById(R.id.reviewNickname)
        val content: TextView = itemView.findViewById(R.id.reviewContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.review_layout, parent, false)
        return ReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        holder.nickname.text = review.nickname
        holder.content.text = review.content
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun addReview(review: Review) {
        reviewList.add(review)
        notifyDataSetChanged()
    }
}
