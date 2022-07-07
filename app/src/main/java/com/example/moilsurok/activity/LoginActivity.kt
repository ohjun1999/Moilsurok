package com.example.moilsurok.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moilsurok.DataClassUser
import com.example.moilsurok.adapter.ListAdapter
import com.example.moilsurok.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var adapter: ListAdapter


    val loginPhoneNumber = binding.loginPhoneNumber.toString()
//    val userPhoneNumber = userList.phoneNumber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)







    }
}