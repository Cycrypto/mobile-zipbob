package com.example.hansotbob.adapter

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hansotbob.R


class MainRecyclerViewAdapter(private val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER = 0
    private val TYPE_CELL = 1

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> TYPE_HEADER
            else -> TYPE_CELL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_layout, parent, false))
            TYPE_CELL -> CellViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_card_small, parent, false))
            else -> throw IllegalArgumentException("Illegal View type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CellViewHolder) {
            holder.bindData("Item #$position")
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1 // 헤더 추가로 인해 +1
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class CellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bindData(data: String) {
            textView.text = data
        }
    }
}
