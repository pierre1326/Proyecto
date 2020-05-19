package com.tec.proyecto.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tec.proyecto.ModifyActivity
import com.tec.proyecto.R

class ViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter, parent, false)) {

  private var title: TextView? = null;
  private var author: TextView? = null;
  private var description: TextView? = null;

  private var update: ImageButton? = null;
  private var remove: ImageButton? = null;

  init {
    title = itemView.findViewById(R.id.title);
    author = itemView.findViewById(R.id.author)
    description = itemView.findViewById(R.id.description);
    update = itemView.findViewById(R.id.buttonUpdate)
    remove = itemView.findViewById(R.id.buttonRemove)
  }

  fun bind(book: Book) {
    title?.text = book.name;
    author?.text = book.author;
    description?.text = book.description;
    remove?.setTag(R.id.key, book.id)
    remove?.setOnClickListener(book.listener)
    update?.setOnClickListener {
      val intent = Intent(itemView.context, ModifyActivity::class.java)
      intent.putExtra("id", book.id)
      intent.putExtra("name", book.name)
      intent.putExtra("author", book.author)
      intent.putExtra("description", book.description)
      itemView.context.startActivity(intent)
    }
  }

}