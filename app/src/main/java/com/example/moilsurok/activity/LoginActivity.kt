package com.example.moilsurok.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.moilsurok.adapter.ListAdapter
import com.example.moilsurok.dataClass.UserDataClass
import com.example.moilsurok.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    var firestore: FirebaseFirestore? = null
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loginEnter.setOnClickListener {
            goPhoneNum()


        }
        binding.loginPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

//       val status = ContextCompat.checkSelfPermission(this,"android.permission.READ_CONTACTS")
//        if ()

    }



    private fun goPhoneNum() {
        var name: String
        var company: String
        var year: String
        var birthdate: String
        var phoneNum: String
        var email: String
        var department: String
        var comPosition: String
        var comTel: String
        var comAdr: String
        var faxNum: String
        var id: String
        val logPhoneNum = binding.loginPhoneNumber.text.toString()
        val login = db.collection("teams").document("FxRFio9hTwGqAsU5AIZd")
            .collection("User").whereEqualTo("phoneNum", logPhoneNum).whereEqualTo("check", "O")
        login
            .get()
            //IF문 사용해서 빈값을 받아왔을 때 실패 메시지 document를 받아왔을 때 액티비티 이동
            .addOnSuccessListener { documents ->


                for (document in documents) {
                    id = document.id
                    name = document.getString("name").toString()
                    company = document.getString("company").toString()
                    year = document.getString("year").toString()
                    birthdate = document.getString("birthdate").toString()
                    phoneNum = document.getString("phoneNum").toString()
                    email = document.getString("email").toString()
                    department = document.getString("department").toString()
                    comPosition = document.getString("comPosition").toString()
                    comTel = document.getString("comTel").toString()
                    comAdr = document.getString("comAdr").toString()
                    faxNum = document.getString("faxNum").toString()


                    Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("content", "원하는 데이터를 보냅니다.")
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
                    intent.putExtra("id", id)
                    startActivity(intent)

                    finish()

                }

            }
            //경로가 실패했을 때
            .addOnFailureListener { exception ->
                Toast.makeText(this, "등록되지 않은 번호입니다.", Toast.LENGTH_SHORT).show()
            }

    }


    // 유저정보 넘겨주고 메인 액티비티 호출

}