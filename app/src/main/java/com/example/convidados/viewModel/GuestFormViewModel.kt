package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    //tem que usar AndroidViewModel pq não tem context

    private val context = application.applicationContext //essa variavel está aqui para pode passar o context ali embaixo
    //que foi passado através do application por AndroidViewMode
    private val guestRepository: GuestRepository = GuestRepository.getInstance(context)

    private var _saveGuest = MutableLiveData<Boolean>()
    val saveGuest : LiveData<Boolean> = _saveGuest

    fun save(name: String, presence: Boolean){
        val guest = GuestModel(name= name, presence= presence)
        _saveGuest.value = guestRepository.save(guest)
    }
}