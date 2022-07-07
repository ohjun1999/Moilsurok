package com.example.moilsurok.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.activity.UserDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings

class NoteAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var daNote : ArrayList<UserDataClass> = arrayListOf()

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}