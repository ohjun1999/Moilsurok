package com.example.moilsurok

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private val repo = Repo()
    fun fetchData(): LiveData<MutableList<DataClassUser>> {
        val mutableData = MutableLiveData<MutableList<DataClassUser>>()
        repo.getData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}