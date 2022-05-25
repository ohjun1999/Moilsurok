package com.example.moilsurok.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moilsurok.R
import com.example.moilsurok.databinding.ActivityAssociationInquiryBinding
import com.example.moilsurok.databinding.ActivityDateBinding
import java.util.*


class DateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityDateBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        binding.backKey.setOnClickListener {
            finish()
        }
    }
}