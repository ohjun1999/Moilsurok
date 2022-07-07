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
        //DB로부터 데이터를 읽을 참조 위치 설정
        val myRef = database.getReference("teams").child("FxRFio9hTwGqAsU5AIZd").child("User")
        //참조한 위치에 이벤트 리스너를 연결
        //변화가 일어날 때마다 매번 데이터를 읽어옴
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