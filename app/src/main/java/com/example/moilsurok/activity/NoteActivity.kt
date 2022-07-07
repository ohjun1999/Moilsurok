package com.example.moilsurok.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.DataClassUser
import com.example.moilsurok.ListViewModel
import com.example.moilsurok.R
import com.example.moilsurok.adapter.ListAdapter
import com.example.moilsurok.databinding.ActivityNoteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User


class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private lateinit var database: DatabaseReference
    private lateinit var adapter:ListAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }
    var userList = arrayListOf<DataClassUser>()

    var firestore : FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityNoteBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        // 파이어스토어 인스턴스 초기화
        firestore = FirebaseFirestore.getInstance()

        binding.noteRecyclerView.adapter = NoteAdapter()
        binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)

//        val recyclerView : RecyclerView = findViewById(R.id.noteRecyclerView)
//
//
//        adapter = ListAdapter(this, userList)
//
//        for (i in 0..20) {
//            userList.add(DataClassUser("" + "기", "" + "님", "", "","","","","","","",""))
//            //어떻게 데이터 값을 받을 것인지
//        }
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = adapter
//        observerData()

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
//        val sYear = resources.getStringArray(R.array.itemList)
//        val sAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sYear)
//        binding.searchYear.adapter = sAdapter
//
//        val position = resources.getStringArray(R.array.itemList)
//        val pAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, position)
//        binding.searchPosition.adapter = pAdapter



    }

    inner class NoteAdapter :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var deNote : ArrayList<UserDataClass> = arrayListOf()

        init {
            firestore?.collection("teams")?.document("FxRFio9hTwGqAsU5AIZd")?.collection("User")?.addSnapshotListener{ querySnapshot, firebaseFirestoreException ->
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
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
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
                val intent = Intent(holder.itemView?.context, NoteProfileDetailActivity::class.java)
                intent.putExtra("content","원하는 데이터를 보냅니다.")
                intent.putExtra("year",user.year)
                intent.putExtra("name",user.name)
                intent.putExtra("birthDate",user.birthDate)
                intent.putExtra("phoneNum",user.phoneNum)
                intent.putExtra("email",user.email)
                intent.putExtra("company",user.company)
                intent.putExtra("department",user.department)
                intent.putExtra("comPosition",user.comPosition)
                intent.putExtra("comTel",user.comTel)
                intent.putExtra("comAdr",user.comAdr)
                intent.putExtra("faxNum",user.faxNum)
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


        }

    }

//    fun observerData(){
//        viewModel.fetchData().observe(this, Observer {
//            adapter.setListData(it)
//            adapter.notifyDataSetChanged()
//        })
//    }
}
