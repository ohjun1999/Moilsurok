package com.example.moilsurok.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moilsurok.databinding.ActivityExtraBinding

class ExtraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityExtraBinding.inflate(layoutInflater)
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
        val id = intent.getStringExtra("id")

        // 접근 가능
        binding.goAlarmSetting.setOnClickListener {
            val intent = Intent(this, AlarmSettingActivity::class.java)
            startActivity(intent)

        }
        binding.authenticationManage.setOnClickListener {
            val intent = Intent(this, ManageAuthenticationMethodsActivity::class.java)
            startActivity(intent)
        }
        binding.changeProfile.setOnClickListener {
            val intent = Intent(this, NoteProfileChangeActivity::class.java)
            intent.putExtra("company", company)
            intent.putExtra("name", name)
            intent.putExtra("year", year)
            intent.putExtra("birthdate", birthdate)
            intent.putExtra("phoneNum", phoneNum)
            intent.putExtra("email", email)
            intent.putExtra("department", department)
            intent.putExtra("comPosition", comPosition)
            intent.putExtra("comTel", comTel)
            intent.putExtra("comAdr", comAdr)
            intent.putExtra("faxNum", faxNum)
            intent.putExtra("id",id)
            startActivity(intent)
        }
        binding.goTerms.setOnClickListener {
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
        }
        binding.goInquiry.setOnClickListener {
            val intent = Intent(this, InquiryActivity::class.java)
            startActivity(intent)
        }

        binding.backKey.setOnClickListener {
            finish()
        }

        binding.exYear.text = year
        binding.exName.text = name
        binding.exCompany.text = company
    }
}