package com.example.moilsurok.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moilsurok.InquiryDataClass
import com.example.moilsurok.R

import com.example.moilsurok.databinding.ActivityInquiryTextBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class InquiryTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInquiryTextBinding
    var auth : FirebaseAuth? = null
    var firestore : FirebaseFirestore? = null
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



        binding.backKey.setOnClickListener {
            finish()
        }
        binding.goInquiryFire.setOnClickListener {
            var inquiryDataClass = InquiryDataClass()
            inquiryDataClass.uid = auth?.currentUser?.uid
            inquiryDataClass.title = binding.inquiryTitle.text.toString()
            inquiryDataClass.content = binding.inquiryContent.text.toString()
            inquiryDataClass.pubDate = System.currentTimeMillis()

            firestore?.collection("teams")?.document("FxRFio9hTwGqAsU5AIZd")?.collection("Question")?.document()?.set(inquiryDataClass)
            Toast.makeText(this,"문의가 접수 되었습니다",Toast.LENGTH_SHORT).show()

        }
    }
}