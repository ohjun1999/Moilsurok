package com.example.moilsurok

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.dataClass.UserDataClass

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.noteName)
    val phoneNum: TextView = itemView.findViewById(R.id.phoneNum)
    val email: TextView = itemView.findViewById(R.id.mailAdress)
    val company: TextView = itemView.findViewById(R.id.companyName)
    val year: TextView = itemView.findViewById(R.id.noteYear)
    val comPosition: TextView = itemView.findViewById(R.id.companyInfo)

    // ViewHolder와 instaData 클래스의 각 변수를 연동하는 역할
    fun bind(user: UserDataClass) {

        name.text = user.name
        phoneNum.text = user.phoneNum
        email.text = user.email
        company.text = user.company
        year.text = user.year
        comPosition.text = user.comPosition
    }
}