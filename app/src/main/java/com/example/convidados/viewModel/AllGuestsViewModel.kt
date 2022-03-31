package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    //pede() um context fazer com application
    private val guestRepository = GuestRepository.getInstance(application.applicationContext)
    //guestRepository é o acesso aos dados

    private val _guestList = MutableLiveData<List<GuestModel>>()

    val guestList: LiveData<List<GuestModel>> = _guestList

    //load vai carregar a lista de usuarios - p/ preciso acessar o repositório
    fun load(){
       _guestList.value =  guestRepository.getAll()
    }
}