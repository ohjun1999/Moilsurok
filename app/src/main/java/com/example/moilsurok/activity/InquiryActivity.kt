package com.example.moilsurok.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moilsurok.R
import com.example.moilsurok.databinding.ActivityInquiryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class InquiryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInquiryBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityInquiryBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)


        binding.backKey.setOnClickListener {
            finish()
        }
        binding.goRealInquiry.setOnClickListener {
            val intent = Intent(this,InquiryTextActivity::class.java)
            startActivity(intent)
        }

    }
}