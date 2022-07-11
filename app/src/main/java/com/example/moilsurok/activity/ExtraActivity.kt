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
    }
}