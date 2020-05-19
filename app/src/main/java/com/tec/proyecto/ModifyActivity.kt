package com.tec.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_modify.*

class ModifyActivity : AppCompatActivity(), View.OnClickListener {

  private var create = true

  private var id = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_modify)
    submit.setOnClickListener(this)
    getValues()
  }

  private fun getValues() {
    if(intent.getStringExtra("id") != null) {
      id = intent.getStringExtra("id")
      name.setText(intent.getStringExtra("name"))
      author.setText(intent.getStringExtra("author"))
      description.setText(intent.getStringExtra("description"))
      create = false
    }
  }

  private fun createBook() {
    val query = "INSERT INTO book(Title, Author, Description) VALUES ('${name.text}', '${author.text}', '${description.text}')"
    val thread = Thread(Runnable {
      val database = MainActivity.database
      database.executeUpdate(query)
      val runnable = Runnable {
        Toast.makeText(applicationContext, "Libro Creado", Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext, ListActivity::class.java)
        startActivity(intent)
      }
      runOnUiThread(runnable)
    })
    thread.start()
  }

  private fun updateBook() {
    val query = "UPDATE book SET Title='${name.text}', Author='${author.text}', Description='${description.text}' WHERE book.IdBook=${id}"
    val thread = Thread(Runnable {
      val database = MainActivity.database
      database.executeUpdate(query)
      val runnable = Runnable {
        Toast.makeText(applicationContext, "Libro Actualizado", Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext, ListActivity::class.java)
        startActivity(intent)
      }
      runOnUiThread(runnable)
    })
    thread.start()
  }

  private fun checkValues(): Boolean {
    return !(name.text.toString() == "" || author.text.toString() == "" || description.text.toString() == "")
  }

  override fun onClick(v: View?) {
    if(checkValues()) {
      if(create) {
        createBook()
      }
      else {
        updateBook()
      }
    }
  }

}
