//package com.example.moilsurok.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.moilsurok.R
//import com.example.moilsurok.dataClass.UserDataClass
//import com.google.firebase.firestore.FirebaseFirestore
//
//class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.Holder>() {
//    var firestore: FirebaseFirestore? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
//        firestore = FirebaseFirestore.getInstance()
//        return Holder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return noteList.size
//    }
//
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bind(noteList[position], context)
//    }
//    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val name: TextView = itemView.findViewById(R.id.noteName)
//        val phoneNum: TextView = itemView.findViewById(R.id.phoneNum)
//        val email: TextView = itemView.findViewById(R.id.mailAdress)
//        val company: TextView = itemView.findViewById(R.id.companyName)
//        val year: TextView = itemView.findViewById(R.id.noteYear)
//        val comPosition: TextView = itemView.findViewById(R.id.companyInfo)
//        fun bind(user: UserDataClass, context: Context) {
//            name.text = user.name
//            phoneNum.text = user.phoneNum
//            email.text = user.email
//            company.text = user.company
//            year.text = user.year
//            comPosition.text = user.comPosition
//        }
//    }
//    val first =
//        firestore
//            ?.collection("teams")
//            ?.document("FxRFio9hTwGqAsU5AIZd")
//            ?.collection("User")
//            ?.whereEqualTo("check", "O")
//
//
//    // firebase data 불러오기
//    init {
//        first
//            ?.addSnapshotListener { querySnapshot, _ ->
//                // ArrayList 비워줌
//                noteList.clear()
//
//                for (snapshot in querySnapshot!!.documents) {
//                    var item = snapshot.toObject(UserDataClass::class.java)
//                    noteList.add(item!!)
//
//                }
//                notifyDataSetChanged()
//
//            }
//
//
//    }
//    fun search(serachWord: String, option: String) {
//        firestore?.collection("teams")
//            ?.document("FxRFio9hTwGqAsU5AIZd")
//            ?.collection("User")
//            ?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                // ArrayList 비워줌
//                noteList.clear()
//
//                for (snapshot in querySnapshot!!.documents) {
//                    if (snapshot.getString(option)!!.contains(serachWord)) {
//                        var item = snapshot.toObject(UserDataClass::class.java)
//                        noteList.add(item!!)
//                    }
//                }
//                notifyDataSetChanged()
//            }
//    }
//
//
//}
//
