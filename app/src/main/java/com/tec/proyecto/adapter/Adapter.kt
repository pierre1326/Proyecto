package com.tec.proyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(var list: ArrayList<Book>): RecyclerView.Adapter<ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return ViewHolder(inflater, parent)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val book: Book = list[position]
    holder.bind(book)
  }

  override fun getItemCount(): Int = list.size

}