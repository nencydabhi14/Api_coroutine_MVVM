package com.example.mvvm_pattern.RetrofitIC

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.mvvm_pattern.R

class ProductAdaptor(val apiCallingAll: ApiCallingAll, val listproduct: List<ProductModelItem>) :
    RecyclerView.Adapter<ProductAdaptor.ViewData>() {

    class ViewData(itemView: View) : ViewHolder(itemView) {
        var title_txt = itemView.findViewById<TextView>(R.id.title_txt)
        var category_txt = itemView.findViewById<TextView>(R.id.category_txt)
        var price_txt = itemView.findViewById<TextView>(R.id.price_txt)
        var description_txt = itemView.findViewById<TextView>(R.id.description_txt)
        var img_product = itemView.findViewById<ImageView>(R.id.img_product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(apiCallingAll).inflate(R.layout.item_file, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.title_txt.text = listproduct[position].title
        holder.category_txt.text = listproduct[position].category
        holder.price_txt.text = listproduct[position].price.toString()
        holder.description_txt.text = listproduct[position].description


        Glide.with(apiCallingAll).load(listproduct[position].image).into(holder.img_product)
    }

    override fun getItemCount(): Int {
        return listproduct.size
    }
}