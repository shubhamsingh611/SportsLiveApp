package com.example.sportslive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportslive.R
import com.example.sportslive.model.TennisData

class TennisDataAdapter(private val tennisDataList : List<TennisData>): RecyclerView.Adapter<TennisDataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.event_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtTime.text = tennisDataList[position].openDate
        holder.txtEventName.text = tennisDataList[position].eventName

    }

    override fun getItemCount(): Int {
        return tennisDataList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var txtTime: TextView = itemView.findViewById<TextView>(R.id.tv_time)
        var txtEventName: TextView = itemView.findViewById<TextView>(R.id.tv_event_name)
    }
}