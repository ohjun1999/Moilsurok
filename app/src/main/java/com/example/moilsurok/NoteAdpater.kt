package com.example.moilsurok

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NoteAdapter(private val context: Context) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var datas = mutableListOf<NoteData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.noteName)
        private val txtYear: TextView = itemView.findViewById(R.id.noteYear)
        private val noteImage: ImageView = itemView.findViewById(R.id.noteImage)
        private val phoneNumber: TextView = itemView.findViewById(R.id.phoneNumber)
        private val mailAdress: TextView = itemView.findViewById(R.id.mailAdress)
        private val companyInfo: TextView = itemView.findViewById(R.id.companyInfo)

        fun bind(item: NoteData) {
            txtName.text = item.name
            txtYear.text = item.year.toString()
            Glide.with(itemView).load(item.img).into(noteImage)
            phoneNumber.text = item.num.toString()
            

        }
    }


}