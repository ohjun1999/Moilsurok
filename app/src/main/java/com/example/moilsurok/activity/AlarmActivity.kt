package com.example.moilsurok.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.R
import com.example.moilsurok.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        binding.backKey.setOnClickListener {
            finish()
        }

        val alarmList = mutableListOf<Alarm>()
        for (i in 0..100) {
            alarmList.add(Alarm())
            //어떻게 데이터 값을 받을 것인지
        }

        val recyclerView = findViewById<RecyclerView>(R.id.alarmRecyclerView)
        //리사이클러뷰에 어답터 장착
        recyclerView.adapter = AlarmAdapter(alarmList, LayoutInflater.from(this))
        //리사이클러뷰에 레이아웃 매니저 장착
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

}


class Alarm(

)

class AlarmAdapter(
    var alarmList: MutableList<Alarm>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //아이템 뷰의 상세 뷰 컴포넌트를 홀드(id선택)한다.
        val alarmDate: TextView
        val alarmTitle: TextView

        init {
            alarmDate = itemView.findViewById(R.id.registrationDate)
            alarmTitle = itemView.findViewById(R.id.whatAlarm)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        //아이템 뷰를 리턴
        val view = inflater.inflate(R.layout.item_alarm, parent, false)
        return AlarmViewHolder(view)
    }

    override fun getItemCount(): Int {
        //전체 데이터의 크기(갯수) 리턴
        return alarmList.size
    }


    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {

    }


}