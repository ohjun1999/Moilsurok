package com.example.moilsurok.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.R
import com.example.moilsurok.databinding.ActivityNoteBinding


class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityNoteBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        val noteList = mutableListOf<Note>()
        for (i in 0..100) {
            noteList.add(Note("" + "님", "" + "기", "-" + "-", "", ""))
            //어떻게 데이터 값을 받을 것인지
        }

        val recyclerView = findViewById<RecyclerView>(R.id.noteRecyclerView)
        //리사이클러뷰에 어답터 장착
        recyclerView.adapter = NoteAdapter(noteList, LayoutInflater.from(this))
        //리사이클러뷰에 레이아웃 매니저 장착
        recyclerView.layoutManager = LinearLayoutManager(this)



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


    }

}

class Note(
    val name: String,
    val year: String,
    val num: String,
    val adress: String,
    val info: String
)

class NoteAdapter(
    var noteList: MutableList<Note>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //아이템 뷰의 상세 뷰 컴포넌트를 홀드(id선택)한다.
        val txtName: TextView
        val txtYear: TextView
        val noteImage: ImageView
        val phoneNumber: TextView
        val mailAdress: TextView
        val companyInfo: TextView

        init {
            txtName = itemView.findViewById(R.id.noteName)
            txtYear = itemView.findViewById(R.id.noteYear)
            noteImage = itemView.findViewById(R.id.noteImage)
            phoneNumber = itemView.findViewById(R.id.phoneNumber)
            mailAdress = itemView.findViewById(R.id.mailAdress)
            companyInfo = itemView.findViewById(R.id.companyInfo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        //아이템 뷰를 리턴
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        //전체 데이터의 크기(갯수) 리턴
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

    }


}

