package com.tec.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tec.proyecto.adapter.Adapter
import com.tec.proyecto.adapter.Book
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), View.OnClickListener {

  private var books = ArrayList<Book>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)
    buttonAdd.setOnClickListener {
      val intent = Intent(applicationContext, ModifyActivity::class.java)
      startActivity(intent)
    }
  }

  override fun onResume() {
    super.onResume()
    getBooks()
  }

  private fun getBooks() {
    val query = "SELECT IdBook, Title, Author, Description FROM book"
    val thread = Thread(Runnable {
      val books = ArrayList<Book>()
      val database = MainActivity.database
      val result = database.executeQuery(query)
      if(result != null) {
        while(result.next()) {
          val book = Book(result.getString("IdBook"), result.getString("Title"), result.getString("Author"), result.getString("Description"), this)
          books.add(book)
        }
        val adapter = Adapter(books)
        val runnable = Runnable {
          this.books = books
          listElement.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
          }
        }
        runOnUiThread(runnable)
      }
    })
    thread.start()
  }

  private fun deleteElement(id: String) {
    books.removeIf { t -> t.id == id }
    updateList()
  }

  private fun deleteBook(id: String) {
    val query = "DELETE FROM book WHERE IdBook=${id}"
    val thread = Thread(Runnable {
      val database = MainActivity.database
      database.executeUpdate(query)
    })
    thread.start()
  }

  private fun updateList() {
    listElement.apply {
      adapter?.notifyDataSetChanged()
    }
    Toast.makeText(applicationContext, "Libro eliminado", Toast.LENGTH_SHORT).show()
  }

  override fun onClick(v: View?) {
    val id = v?.getTag(R.id.key).toString()
    deleteElement(id)
    deleteBook(id)
  }

}
