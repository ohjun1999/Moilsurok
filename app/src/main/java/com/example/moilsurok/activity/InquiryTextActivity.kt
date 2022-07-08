package com.example.moilsurok.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.moilsurok.dataClass.InquiryDataClass

import com.example.moilsurok.databinding.ActivityInquiryTextBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class InquiryTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInquiryTextBinding
    var auth: FirebaseAuth? = null
    var firestore: FirebaseFirestore? = null
//    private val database by lazy { FirebaseDatabase.getInstance() }
//    private val userRef = database.getReference("teams")
//    private val userRefChild = userRef.child("FxRFio9hTwGqAsU5AIZd")
//    private val question = userRefChild.child("Question")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityInquiryTextBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()
        val now = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd kk:mm", Locale("ko", "KR"))
        val nowDate = dateFormat.format(date)

        binding.constraintLayout.setOnClickListener {
            hideKeyboard()
        }

        binding.backKey.setOnClickListener {
            finish()
        }
        binding.goInquiryFire.setOnClickListener {
            val intent = Intent(this, InquiryActivity::class.java)


            var inquiryDataClass = InquiryDataClass()
            inquiryDataClass.uid = auth?.currentUser?.uid
            inquiryDataClass.title = binding.inquiryTitle.text.toString()
            inquiryDataClass.content = binding.inquiryContent.text.toString()
            inquiryDataClass.pubDate = nowDate.toString()
            inquiryDataClass.check = "X"


            firestore?.collection("teams")?.document("FxRFio9hTwGqAsU5AIZd")?.collection("Question")
                ?.document()?.set(inquiryDataClass)
            Toast.makeText(this, "문의가 접수 되었습니다", Toast.LENGTH_SHORT).show()

            intent.putExtra("title", inquiryDataClass.title.toString())
            intent.putExtra("pubDate", inquiryDataClass.pubDate.toString())
            intent.putExtra("uid", inquiryDataClass.uid.toString())



            finish()

        }


    }

    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.inquiryContent.windowToken, 0)
    }
}



