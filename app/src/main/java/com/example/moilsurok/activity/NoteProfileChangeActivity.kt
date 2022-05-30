package com.example.moilsurok.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.moilsurok.R
import com.example.moilsurok.databinding.ActivityNoteBinding
import com.example.moilsurok.databinding.ActivityNoteProfileChangeBinding

class NoteProfileChangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteProfileChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityNoteProfileChangeBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        binding.backKey.setOnClickListener {
            finish()
        }
        binding.request.setOnClickListener {
            binding.request.visibility = View.GONE
        }

        binding.cancellationRequest.setOnClickListener {
            binding.request.visibility = View.VISIBLE
        }

    }
}