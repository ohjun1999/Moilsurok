package com.example.moilsurok.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.R
import com.example.moilsurok.databinding.HomeActivityBinding
import com.example.moilsurok.fragment.Example1Fragment

class HomeActivity : AppCompatActivity() {

    internal lateinit var binding: HomeActivityBinding

//    private val examplesAdapter = HomeOptionsAdapter {
//        val fragment = it.createView()

//    }

    fun moveToFirst(){
        var dateFragment = Example1Fragment()
        supportFragmentManager.beginTransaction()

            .add(R.id.homeContainer, dateFragment)
            .addToBackStack("Example1Fragment")
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.homeToolbar)
        binding.examplesRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//            adapter = examplesAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            moveToFirst()
        }
    }



//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            android.R.id.home -> onBackPressed().let { true }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}


