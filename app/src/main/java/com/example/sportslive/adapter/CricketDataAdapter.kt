package com.example.sportslive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportslive.R
import com.example.sportslive.model.CricketData

class CricketDataAdapter(private val cricketDataList : List<CricketData>): RecyclerView.Adapter<CricketDataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.event_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTime.text = cricketDataList[position].openDate
        holder.txtEventName.text = cricketDataList[position].eventName

    }

    override fun getItemCount(): Int {
        return cricketDataList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txtTime: TextView = itemView.findViewById(R.id.tv_time)
        var txtEventName: TextView = itemView.findViewById(R.id.tv_event_name)
    }
}