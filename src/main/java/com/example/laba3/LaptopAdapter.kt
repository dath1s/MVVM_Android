package com.example.laba3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LaptopAdapter(var laptopList: List<LaptopData>) : RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {

    var onItemClick : ((LaptopData) -> Unit)? = null

    inner class LaptopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleData : TextView = itemView.findViewById(R.id.laptopModel)
        val ramData : TextView = itemView.findViewById(R.id.ram)
        val procData : TextView = itemView.findViewById(R.id.proc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return LaptopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return laptopList.size
    }

    override fun onBindViewHolder(holder: LaptopViewHolder, pos: Int) {
        holder.titleData.text = laptopList[pos].title
        holder.ramData.text = "ОЗУ: " + laptopList[pos].RAM.toString() + " Гб"
        holder.procData.text = "Процессор: " + laptopList[pos].proc.toString()

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(laptopList[pos])
        }
    }

    fun setFilteredList(laptopList: List<LaptopData>){
        this.laptopList = laptopList
        notifyDataSetChanged()
    }
}