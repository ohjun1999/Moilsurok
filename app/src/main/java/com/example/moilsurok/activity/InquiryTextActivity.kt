package com.example.moilsurok.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moilsurok.R

import com.example.moilsurok.databinding.ActivityInquiryTextBinding

class InquiryTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInquiryTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityInquiryTextBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        binding.backKey.setOnClickListener {
            finish()
        }
    }
}