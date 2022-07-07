package com.example.moilsurok.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moilsurok.R
import com.example.moilsurok.adapter.HomeOptionsAdapter
import com.example.moilsurok.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // lateinit 사용
    private lateinit var binding: ActivityMainBinding



    private val examplesAdapter = HomeOptionsAdapter {
        val fragment = it.createView()
        supportFragmentManager.beginTransaction()

            .add(R.id.homeContainer, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityMainBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)


        // 접근 가능
        binding.goAlarm.setOnClickListener {
            val intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        }
        binding.goExtra.setOnClickListener {
            val intent = Intent(this, ExtraActivity::class.java)
            startActivity(intent)
        }
        binding.goNote.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
        binding.goNotice.setOnClickListener {
            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }
        binding.goDate.setOnClickListener {
            val intent = Intent(this, DateActivity1::class.java)
            startActivity(intent)

        }
        binding.goExplain.setOnClickListener {
            val intent = Intent(this, ExplainGroupActivity::class.java)
            startActivity(intent)
        }


    }
}