package com.example.moilsurok.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.R
import com.example.moilsurok.dataClass.ScheduleDataClass
import com.google.firebase.firestore.FirebaseFirestore

class ScheduleAdapter () :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var deSchedule = mutableListOf<ScheduleDataClass>()

    var firestore: FirebaseFirestore? = null

    init {
        firestore?.collection("teams")
            ?.document("FxRFio9hTwGqAsU5AIZd")
            ?.collection("Schedule")
            ?.addSnapshotListener{ querySnapshot, _ ->
                deSchedule.clear()

                for (snapshot in querySnapshot!!.documents){
                    var item = snapshot.toObject(ScheduleDataClass::class.java)
                    deSchedule.add(item!!)
                }
                notifyDataSetChanged()
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int = deSchedule.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        holder.bind(deSchedule[position])


    }




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scheduleDate: TextView = itemView.findViewById(R.id.scheduleDate)
        val scheduleTitle: TextView = itemView.findViewById(R.id.scheduleTitle)

        fun bind(schedule: ScheduleDataClass) {
            scheduleTitle.text = schedule.title
            scheduleDate.text = schedule.date
        }


    }

}