package com.example.moilsurok.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moilsurok.databinding.ActivityDateDetailBinding
import com.example.moilsurok.databinding.ActivityMainBinding
import com.example.moilsurok.databinding.ActivityNoteProfileChangeBinding
import com.example.moilsurok.databinding.ActivityProfileDetailBinding

class NoteProfileDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityProfileDetailBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        binding.backKey.setOnClickListener {
            finish()
        }
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val name = intent.getStringExtra("name")
        val year = intent.getStringExtra("year")
        val company = intent.getStringExtra("company")
        val email = intent.getStringExtra("email")
        val position = intent.getStringExtra("position")

        binding.dePhoneNumber.text = phoneNumber
        binding.deName.text = name
        binding.deYear.text = year
        binding.deMailAdress.text = email
        binding.deCompany.text = company
        binding.dePosition.text = position


    }
}