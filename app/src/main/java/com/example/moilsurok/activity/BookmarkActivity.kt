package com.example.moilsurok.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moilsurok.databinding.ActivityBookmarkBinding
import com.example.moilsurok.databinding.ActivityDateDetailBinding

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        binding.backKey.setOnClickListener {
            finish()
        }

    }
}