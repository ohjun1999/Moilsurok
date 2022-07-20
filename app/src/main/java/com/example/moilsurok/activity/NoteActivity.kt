package com.example.moilsurok.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.R
import com.example.moilsurok.dataClass.UserBookMarkDataClass
import com.example.moilsurok.dataClass.UserDataClass
import com.example.moilsurok.databinding.ActivityNoteBinding
import com.google.firebase.firestore.FirebaseFirestore


class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding


    var firestore: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityNoteBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        val id = intent.getStringExtra("id")

        binding.noteRecyclerView.adapter = NoteAdapter()
        binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)



        binding.backKey.setOnClickListener {
            finish()
        }

        binding.menuBtn.setOnClickListener {
            binding.search.visibility = View.VISIBLE
            binding.menuBtn2.visibility = View.VISIBLE
        }

        binding.menuBtn2.setOnClickListener {
            binding.search.visibility = View.GONE
            binding.menuBtn2.visibility = View.GONE

        }

        binding.goBookmark.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }


    }


    inner class NoteAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var deBookMark: ArrayList<UserBookMarkDataClass> = arrayListOf()
        var deNote: ArrayList<UserDataClass> = arrayListOf()
        val first =
            firestore
                ?.collection("teams")
                ?.document("FxRFio9hTwGqAsU5AIZd")
                ?.collection("User")
                ?.whereEqualTo("check","O")


        // firebase data 불러오기
        init {
            first
                ?.addSnapshotListener { querySnapshot, _ ->
                    // ArrayList 비워줌
                    deNote.clear()

                    for (snapshot in querySnapshot!!.documents) {
                        var item = snapshot.toObject(UserDataClass::class.java)
                        deNote.add(item!!)

                    }
                    notifyDataSetChanged()

                }


        }


        // xml 파일을 inflate 하여 ViewHolder 를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

            return ViewHolder(view)
        }

        inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        }

        // onCreateViewHolder 에서 만든 view 와 실제 데이터를 연결
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView
            val user: UserDataClass = deNote[position]
            holder.name.text = user.name
            holder.phoneNum.text = user.phoneNum
            holder.email.text = user.email
            holder.company.text = user.company
            holder.year.text = user.year
            holder.comPosition.text = user.comPosition








            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, NoteProfileDetailActivity::class.java)
                intent.putExtra("content", "원하는 데이터를 보냅니다.")
                intent.putExtra("year", user.year)
                intent.putExtra("name", user.name)
                intent.putExtra("birthdate", user.birthdate)
                intent.putExtra("phoneNum", user.phoneNum)
                intent.putExtra("email", user.email)
                intent.putExtra("company", user.company)
                intent.putExtra("department", user.department)
                intent.putExtra("comPosition", user.comPosition)
                intent.putExtra("comTel", user.comTel)
                intent.putExtra("comAdr", user.comAdr)
                intent.putExtra("faxNum", user.faxNum)
                ContextCompat.startActivity(holder.itemView.context, intent, null)

            }


        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return deNote.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.noteName)
            val phoneNum: TextView = itemView.findViewById(R.id.phoneNum)
            val email: TextView = itemView.findViewById(R.id.mailAdress)
            val company: TextView = itemView.findViewById(R.id.companyName)
            val year: TextView = itemView.findViewById(R.id.noteYear)
            val comPosition: TextView = itemView.findViewById(R.id.companyInfo)
            val check: CheckBox = itemView.findViewById(R.id.bookmark)



        }

    }


}