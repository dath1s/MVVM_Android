package com.example.laba3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var laptopList = ArrayList<LaptopData>()
    private lateinit var adapter: LaptopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addDataToList()
        adapter = LaptopAdapter(laptopList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0)
                return true
            }

        })
    }

    private fun filterList(query : String?){
        if (query != null){
            val filteredList = ArrayList<LaptopData>()
            for (i in laptopList){
                if (i.title.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()){
                Toast.makeText(this, "Ничего не найдено", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList(){
        laptopList.add(LaptopData("MSI", 16, "Pentium"))
        laptopList.add(LaptopData("Lenovo", 4, "Pentium"))
        laptopList.add(LaptopData("DEXP", 6, "Pentium"))
        laptopList.add(LaptopData("MSI", 16, "Pentium"))
        laptopList.add(LaptopData("Lenovo", 4, "Pentium"))
        laptopList.add(LaptopData("DEXP", 6, "Pentium"))
        laptopList.add(LaptopData("MSI", 16, "Pentium"))
        laptopList.add(LaptopData("Lenovo", 4, "Pentium"))
        laptopList.add(LaptopData("DEXP", 6, "Pentium"))
        laptopList.add(LaptopData("MSI", 16, "Pentium"))
        laptopList.add(LaptopData("Lenovo", 4, "Pentium"))
        laptopList.add(LaptopData("DEXP", 6, "Pentium"))
    }
}