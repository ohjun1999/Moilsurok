package com.example.moilsurok.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OneShotPreDrawListener.add
import com.example.moilsurok.NoteAdapter
import com.example.moilsurok.NoteData
import com.example.moilsurok.R
import com.example.moilsurok.databinding.ActivityDateBinding
import com.example.moilsurok.databinding.ActivityNoteBinding


class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding

    lateinit var noteAdapter: NoteAdapter
    val datas = mutableListOf<NoteData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityNoteBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

//        initRecycler()

        binding.backKey.setOnClickListener {
            finish()
        }

        binding.menuBtn.setOnClickListener {
            binding.search.visibility = View.VISIBLE
            binding.menuBtn2.visibility = View.VISIBLE
        }

        binding.menuBtn2.setOnClickListener {
            binding.search.visibility = View.GONE
            binding.menuBtn2.visibility = View.GONE
        }
//
//        fun initRecycler() {
//            noteAdapter = NoteAdapter(this)
//            binding.recyclerview.adapter = noteAdapter
//
//
//            datas.apply {
//                add(NoteData(img = R.drawable.profile1, name = "mary", age = 24))
//                add(NoteData(img = R.drawable.profile3, name = "jenny", age = 26))
//                add(NoteData(img = R.drawable.profile2, name = "jhon", age = 27))
//                add(NoteData(img = R.drawable.profile5, name = "ruby", age = 21))
//                add(NoteData(img = R.drawable.profile4, name = "yuna", age = 23))
//
//                NoteAdapter.datas = datas
//                NoteAdapter.notifyDataSetChanged()
//
//            }

    }
}