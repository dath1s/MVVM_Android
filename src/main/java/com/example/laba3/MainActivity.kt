package com.example.laba3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var laptopList = ArrayList<LaptopData>()
    private lateinit var adapter: LaptopAdapter

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf("-", "4 Гб", "8 Гб", "16 Гб")
        var selectedRam = "-"
        val autoComplete: AutoCompleteTextView = findViewById(R.id.droplist)
        val adapterDrop = ArrayAdapter(this, R.layout.list_item, items)
        autoComplete.setAdapter(adapterDrop)
        autoComplete.onItemClickListener = AdapterView.OnItemClickListener { AdapterView, view, i, l ->
            val itemSelected = adapterDrop.getItem(i)
            selectedRam = itemSelected.toString()
            filterList(findViewById<SearchView>(R.id.searchView).query.toString(), selectedRam)
        }

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addDataToList()
        val db = DataBaseHandler(this, null)
        val cursor = db.getAll()
        cursor!!.moveToFirst()
        laptopList.add(LaptopData(
            cursor.getString(cursor.getColumnIndex("model")),
            cursor.getString(cursor.getColumnIndex("ram")).toInt(),
            cursor.getString(cursor.getColumnIndex("proc"))
        ))
        while(cursor.moveToNext()){
            laptopList.add(LaptopData(
                cursor.getString(cursor.getColumnIndex("model")),
                cursor.getString(cursor.getColumnIndex("ram")).toInt(),
                cursor.getString(cursor.getColumnIndex("proc"))
            ))
        }
        cursor.close()


        adapter = LaptopAdapter(laptopList)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("laptop", it)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0, selectedRam)
                return true
            }

        })
    }

    private fun filterList(query: String?, selectedRam: String?) {
        if (query != null) {
            val filteredList = ArrayList<LaptopData>()
            for (i in laptopList) {
                if (i.title == query) {
                    if (selectedRam != null) {
                        if (selectedRam != "-") {
                            if (selectedRam == i.RAM.toString() + " Гб") {
                                filteredList.add(i)
                            }
                        } else {
                            filteredList.add(i)
                        }
                    } else {
                        filteredList.add(i)
                    }
                }
            }

            if (filteredList.isEmpty()) {
                adapter.setFilteredList(listOf())
                Toast.makeText(this, "Ничего не найдено", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
//        laptopList.add(LaptopData("MSI", 16, "Pentium"))
        val db = DataBaseHandler(this, null)
        db.dropDB()

        db.insertData(Laptop("MSI", 16, "Pentium"))
        db.insertData(Laptop("Lenovo", 4, "Pentium"))
        db.insertData(Laptop("DEXP", 8, "Pentium"))
        db.insertData(Laptop("MSI", 8, "Pentium"))
        db.insertData(Laptop("Lenovo", 4, "Pentium"))
        db.insertData(Laptop("DEXP", 8, "Pentium"))
        db.insertData(Laptop("MSI", 4, "Pentium"))
        db.insertData(Laptop("Lenovo", 4, "Pentium"))
        db.insertData(Laptop("DEXP", 8, "Pentium"))
        db.insertData(Laptop("MSI", 4, "Pentium"))
        db.insertData(Laptop("Lenovo", 4, "Pentium"))
        db.insertData(Laptop("DEXP", 6, "Pentium"))
    }
}