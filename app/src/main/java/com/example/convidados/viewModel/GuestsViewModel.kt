package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    //pede() um context fazer com application
    private val guestRepository  = GuestRepository(application.applicationContext)
    //guestRepository é o acesso aos dados

    private val _guestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = _guestList

    //load vai carregar a lista de usuarios - p/ preciso acessar o repositório
    fun load(filter: Int) {

        if (filter == GuestConstants.FILTER.EMPTY) {
            _guestList.value = guestRepository.getAll()
        } else if (filter == GuestConstants.FILTER.PRESENT) {
            _guestList.value = guestRepository.getPresence()
        } else {
            _guestList.value = guestRepository.getAbsent()
        }
    }

    fun delete(id: Int) {
        val guest = guestRepository.getGuest(id)
        guestRepository.delete(guest)
    }
}