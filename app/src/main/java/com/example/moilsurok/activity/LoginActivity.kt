package com.example.moilsurok.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moilsurok.DataClassUser
import com.example.moilsurok.ListViewModel
import com.example.moilsurok.adapter.ListAdapter
import com.example.moilsurok.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var adapter: ListAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }
    var userList = arrayListOf<DataClassUser>()

    val loginPhoneNumber = binding.loginPhoneNumber.toString()
//    val userPhoneNumber = userList.phoneNumber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//
//        binding.loginEnter.setOnClickListener {
//            if (loginPhoneNumber == userPhoneNumber) {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//
//            }
//        }


        fun observerData() {
            viewModel.fetchData().observe(this, Observer {
                adapter.setListData(it)
                adapter.notifyDataSetChanged()
            })

        }
    }
}