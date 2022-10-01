package com.example.mvvm_pattern.PaginationAPI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mvvm_pattern.R

class PaginationAdaptor(val readDataScreen: ReadDataScreen, val list: List<ApiModelClassitemItem>) :
    RecyclerView.Adapter<PaginationAdaptor.ViewData>() {

    class ViewData(itemView: View) : ViewHolder(itemView) {
        var id = itemView.findViewById<TextView>(R.id.id)
        var name = itemView.findViewById<TextView>(R.id.name)
        var email = itemView.findViewById<TextView>(R.id.email)
        var body = itemView.findViewById<TextView>(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view =
            LayoutInflater.from(readDataScreen).inflate(R.layout.itemview_pagination, parent, false)
        return ViewData((view))
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.id.text = list[position].id.toString()
        holder.name.text = list[position].name.toString()
        holder.email.text = list[position].email.toString()
        holder.body.text = list[position].body.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}