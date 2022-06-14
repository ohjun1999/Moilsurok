package com.example.moilsurok.activity

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.DataClassUser
import com.example.moilsurok.ListViewModel
import com.example.moilsurok.R
import com.example.moilsurok.adapter.ListAdapter
import com.example.moilsurok.databinding.ActivityNoteBinding
import com.google.firebase.database.DatabaseReference



class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private lateinit var database: DatabaseReference
    private lateinit var adapter:ListAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }
    var userList = arrayListOf<DataClassUser>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityNoteBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        val recyclerView : RecyclerView = findViewById(R.id.noteRecyclerView)


        adapter = ListAdapter(this, userList)

        for (i in 0..100) {
            userList.add(DataClassUser("" + "님", "" + "기", "", "",""))
            //어떻게 데이터 값을 받을 것인지
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        observerData()

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

    }

    fun observerData(){
        viewModel.fetchData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}
