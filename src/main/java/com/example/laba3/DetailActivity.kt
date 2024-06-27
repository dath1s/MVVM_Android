package com.example.laba3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val laptop = intent.getParcelableExtra<LaptopData>("laptop")
        if (laptop != null) {
            findViewById<TextView>(R.id.laptopModel).text = laptop.title
            findViewById<TextView>(R.id.ram).text = "ОЗУ: " + laptop.RAM.toString() + " Гб"
            findViewById<TextView>(R.id.proc).text = "Процессор: " + laptop.proc
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}