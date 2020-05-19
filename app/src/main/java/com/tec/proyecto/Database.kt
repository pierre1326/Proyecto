package com.tec.proyecto

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.util.*

class Database {

  private val properties = Properties()
  private lateinit var connection: Connection

  fun setProperties(username: String, password: String) {
    properties["user"] = username
    properties["password"] = password
  }

  fun openConnection(ip: String, port: String, database: String) {
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    connection = DriverManager.getConnection("jdbc:mysql://$ip:$port/$database", properties)
  }

  fun closeConnection() {
    connection.close()
  }

  fun executeQuery(query: String): ResultSet? {
    val statement = connection.createStatement()
    return statement.executeQuery(query)
  }

  fun executeUpdate(query: String): Int {
    val statement = connection.createStatement()
    return statement.executeUpdate(query)
  }

}