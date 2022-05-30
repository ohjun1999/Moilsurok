package com.example.moilsurok.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.R
import com.example.moilsurok.databinding.ActivityNoteBinding
import com.example.moilsurok.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityNoticeBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        binding.backKey.setOnClickListener {
            finish()
        }

        val noticeList = mutableListOf<Notice>()
        for (i in 0..100) {
            noticeList.add(Notice())
            //어떻게 데이터 값을 받을 것인지
        }

        val recyclerView = findViewById<RecyclerView>(R.id.noticeRecyclerView)
        //리사이클러뷰에 어답터 장착
        recyclerView.adapter = NoticeAdapter(noticeList, LayoutInflater.from(this))
        //리사이클러뷰에 레이아웃 매니저 장착
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}


class Notice(

)

class NoticeAdapter(
    var noticeList: MutableList<Notice>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        //아이템 뷰의 상세 뷰 컴포넌트를 홀드(id선택)한다.
        val noticeDate: TextView
        val noticeTitle: TextView

        init {
            noticeDate = itemView.findViewById(R.id.registrationDate)
            noticeTitle = itemView.findViewById(R.id.whatNotice)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        //아이템 뷰를 리턴
        val view = inflater.inflate(R.layout.item_notice, parent, false)

        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        //전체 데이터의 크기(갯수) 리턴
        return noticeList.size
    }


    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {

    }


}