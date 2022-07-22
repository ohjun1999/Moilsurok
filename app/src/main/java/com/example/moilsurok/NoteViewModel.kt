package com.example.moilsurok

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    private val baeminRepository = BaeminRepository()
    private val notice: LiveData<Data>
        get() = baeminRepository._note

    fun loadBaeminNote(page: Int){
        baeminRepository.loadBaeminNote(page)
    }

    fun getAll(): LiveData<Data> {
        return notice
    }
}