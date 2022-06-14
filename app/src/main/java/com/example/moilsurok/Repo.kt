package com.example.moilsurok

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Repo {
    fun getData(): LiveData<MutableList<DataClassUser>> {
        val mutableData = MutableLiveData<MutableList<DataClassUser>>()
        val database = Firebase.database
        val myRef = database.getReference("User")
        myRef.addValueEventListener(object : ValueEventListener {
            val listData: MutableList<DataClassUser> = mutableListOf<DataClassUser>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(DataClassUser::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return mutableData
    }
}