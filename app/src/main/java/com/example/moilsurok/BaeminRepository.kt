package com.example.moilsurok

import androidx.lifecycle.MutableLiveData
import com.example.moilsurok.dataClass.UserDataClass
import com.google.firebase.firestore.FirebaseFirestore

class BaeminRepository {
    //private val parameter: MutableMap<String, String> = HashMap()
    var _note = MutableLiveData<Data>()
    var firestore: FirebaseFirestore? = null
    private val deNote: ArrayList<UserDataClass> = arrayListOf()

    fun loadBaeminNote(page: Int) {
        //parameter["page"] = page.toString()

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        val first =
            firestore
                ?.collection("teams")
                ?.document("FxRFio9hTwGqAsU5AIZd")
                ?.collection("User")
                ?.whereEqualTo("check", "O")?.limit(10)


        // firebase data 불러오기

        first
            ?.addSnapshotListener { querySnapshot, _ ->
                // ArrayList 비워줌
                deNote.clear()

                for (snapshot in querySnapshot!!.documents) {
                    var item = snapshot.toObject(UserDataClass::class.java)
                    deNote.add(item!!)


                }


            }


    }
}