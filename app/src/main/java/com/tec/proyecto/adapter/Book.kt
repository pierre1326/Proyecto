package com.tec.proyecto.adapter

import android.view.View

data class Book(val id: String, val name: String, val author: String, val description: String, val listener: View.OnClickListener)