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
        val year = intent.getStringExtra("year")
        val name = intent.getStringExtra("name")
        val birthdate = intent.getStringExtra("birthdate")
        val phoneNum = intent.getStringExtra("phoneNum")
        val email = intent.getStringExtra("email")
        val company = intent.getStringExtra("company")
        val department = intent.getStringExtra("department")
        val comPosition = intent.getStringExtra("comPosition")
        val comTel = intent.getStringExtra("comTel")
        val comAdr = intent.getStringExtra("comAdr")
        val faxNum = intent.getStringExtra("faxNum")

        binding.backKey.setOnClickListener {
            finish()
        }
        binding.request.setOnClickListener {
            binding.request.visibility = View.GONE
            binding.cancellationRequest.visibility = View.VISIBLE

        }

        binding.cancellationRequest.setOnClickListener {
            binding.request.visibility = View.VISIBLE
            binding.cancellationRequest.visibility = View.GONE
        }

        binding.frYear.text = year
        binding.frName.text = name
        binding.frBirthDate.text= birthdate
        binding.frPhoneNum.text = phoneNum
        binding.frEmail.text = email
        binding.frCompany.text = company
        binding.frDepartment.text = department
        binding.frComPosition.text = comPosition
        binding.frComAdr.text = comAdr
        binding.frFaxNum.text = faxNum


    }
}