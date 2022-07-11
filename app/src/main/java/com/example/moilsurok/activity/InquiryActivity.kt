package com.example.moilsurok.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.InquiryBox
import com.example.moilsurok.R
import com.example.moilsurok.databinding.ActivityInquiryBinding
import com.google.firebase.firestore.FirebaseFirestore


class InquiryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInquiryBinding
    var firestore: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityInquiryBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        firestore = FirebaseFirestore.getInstance()

        binding.inquiryRecyclerView.adapter = InquiryAdapter()
        binding.inquiryRecyclerView.layoutManager = LinearLayoutManager(this)




        binding.backKey.setOnClickListener {
            finish()
        }
        binding.goRealInquiry.setOnClickListener {
            val intent = Intent(this, InquiryTextActivity::class.java)
            startActivity(intent)
        }




    }
    inner class InquiryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var deInquiry: ArrayList<InquiryBox> = arrayListOf()
        val first =
            firestore?.collection("teams")?.document("FxRFio9hTwGqAsU5AIZd")
                ?.collection("User")

        // firebase data 불러오기
        init {
            first
                ?.addSnapshotListener { querySnapshot, _ ->

                    deInquiry.clear()
                    for (snapshot in querySnapshot!!.documents) {
                        var item = snapshot.toObject(InquiryBox::class.java)
                        deInquiry.add(item!!)
                    }

                    notifyDataSetChanged()
                }

        }


        // xml 파일을 inflate 하여 ViewHolder 를 생성
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_inquiry, parent, false)





            return ViewHolder(view)
        }

        inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        }

        // onCreateViewHolder 에서 만든 view 와 실제 데이터를 연결
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView
            val inquiry: InquiryBox = deInquiry[position]
            holder.title.text = inquiry.title

//            holder.itemView.setOnClickListener {
//                val intent = Intent(holder.itemView?.context, InquiryDetailActivity::class.java)
//                intent.putExtra("content", "원하는 데이터를 보냅니다.")
//                intent.putExtra("title", inquiry.title)
//
//                ContextCompat.startActivity(holder.itemView.context, intent, null)
//
//            }


        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return deInquiry.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val modifiedDate: TextView = itemView.findViewById(R.id.modifiedDate)
            val title: TextView = itemView.findViewById(R.id.title)


        }

    }
}