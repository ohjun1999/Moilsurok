package com.example.moilsurok.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.DataClassUser
import com.example.moilsurok.R
import com.example.moilsurok.activity.ExtraActivity
import com.example.moilsurok.activity.NoteProfileDetailActivity

class ListAdapter(private val context: Context, private var userList: MutableList<DataClassUser>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    //필터링을 위해 필요한 변수
    private var files: MutableList<DataClassUser> = userList


    fun setListData(data: MutableList<DataClassUser>) {
        userList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val user: DataClassUser = userList[position]
        holder.name.text = user.name
        holder.phoneNumber.text = user.phoneNumber
        holder.email.text = user.email
        holder.company.text = user.company
        holder.year.text = user.year
        holder.position.text = user.position
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.noteName)
        val phoneNumber: TextView = itemView.findViewById(R.id.phoneNumber)
        val email: TextView = itemView.findViewById(R.id.mailAdress)
        val company: TextView = itemView.findViewById(R.id.companyName)
        val year: TextView = itemView.findViewById(R.id.noteYear)
        val position: TextView = itemView.findViewById(R.id.companyInfo)


    }


}

