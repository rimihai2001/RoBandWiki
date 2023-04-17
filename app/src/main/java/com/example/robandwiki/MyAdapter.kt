package com.example.robandwiki

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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

        val btnShare = holder.itemView.findViewById<ImageButton>(R.id.btnShare)

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this band: ${currentitem.bandName} - ${currentitem.bandLocation} (${currentitem.bandGenre})")
            holder.itemView.context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
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
