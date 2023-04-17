package com.example.robandwiki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val bandList : ArrayList<Band>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.band_item, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = bandList[position]
        holder.bandName.text = currentitem.bandName
        holder.bandLocation.text = currentitem.bandLocation
        holder.bandGenre.text = currentitem.bandGenre
    }

    override fun getItemCount(): Int {
        return bandList.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val bandName : TextView = itemView.findViewById(R.id.tvbandName)
        val bandGenre : TextView = itemView.findViewById(R.id.tvbandGenre)
        val bandLocation : TextView = itemView.findViewById(R.id.tvbandLocation)
    }
}
