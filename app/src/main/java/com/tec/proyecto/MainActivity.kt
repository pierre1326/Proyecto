package com.tec.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_modify.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

  companion object {
    lateinit var database: Database
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    buttonLogin.setOnClickListener(this)
  }

  private fun checkValues(): Boolean {
    return !(ip.text.toString() == "" || database.text.toString() == "" || username.text.toString() == "" || password.text.toString() == "")
  }

  private fun initDatabase() {
    val thread = Thread(Runnable {
      MainActivity.database = Database()
      MainActivity.database.setProperties(username.text.toString(), password.text.toString())
      MainActivity.database.openConnection(ip.text.toString(), "3306", this.database.text.toString())
      val runnable = Runnable {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
      }
      runOnUiThread(runnable)
    })
    thread.start()
  }

  override fun onClick(v: View?) {
    if(checkValues()) {
      initDatabase()
    }
  }

}
