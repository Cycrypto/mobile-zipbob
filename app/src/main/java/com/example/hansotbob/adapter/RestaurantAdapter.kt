package com.example.hansotbob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.hansotbob.R
import com.example.hansotbob.data.Restaurant



class RestaurantAdapter(private val restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.restaurant_image)
        val nameTextView: TextView = view.findViewById(R.id.restaurant_name)
        val detailsTextView: TextView = view.findViewById(R.id.restaurant_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.imageView.setImageResource(restaurant.imageResource)
        holder.nameTextView.text = restaurant.name
        holder.detailsTextView.text = restaurant.details
    }

    override fun getItemCount() = restaurantList.size
}

