package com.example.moilsurok.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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


        val phoneNum = binding.loginPhoneNumber.text.toString()
        binding.loginEnter.setOnClickListener {
            goPhoneNum()

        }


    }


    private fun goPhoneNum() {

        db.collection("teams").document("FxRFio9hTwGqAsU5AIZd")
            .collection("User").whereEqualTo("phoneNum", binding.loginPhoneNumber.text.toString())
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "id => data")
                    Toast.makeText(baseContext,"성공",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
                Toast.makeText(baseContext,"실패",Toast.LENGTH_SHORT).show()
            }
    }


    // 유저정보 넘겨주고 메인 액티비티 호출

}