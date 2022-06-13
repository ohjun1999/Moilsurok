package com.example.moilsurok.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.R
import java.util.*

class AdapterDay(val tempMonth:Int, val dayList: MutableList<Date>): RecyclerView.Adapter<AdapterDay.DayView>() {
    val ROW = 6

    inner class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_day, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.layout.findViewById<LinearLayout>(R.id.dayLayoutItem).setOnClickListener {
            Toast.makeText(holder.layout.context, "${dayList[position]}", Toast.LENGTH_SHORT).show()
        }
        holder.layout.findViewById<TextView>(R.id.dayTextItem).text = dayList[position].date.toString()

        holder.layout.findViewById<TextView>(R.id.dayTextItem).setTextColor(when(position % 7) {
            0 -> Color.RED
            6 -> Color.BLUE
            else -> Color.BLACK
        })

        if(tempMonth != dayList[position].month -1) {
            holder.layout.findViewById<TextView>(R.id.dayTextItem).alpha = 0.4f
        }
    }

    override fun getItemCount(): Int {
        return ROW * 7
    }
}