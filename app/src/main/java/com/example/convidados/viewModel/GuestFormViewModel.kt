package com.example.convidados.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    //tem que usar AndroidViewModel pq não tem context

    private val context =
        application.applicationContext //essa variavel está aqui para pode passar o context ali embaixo

    //que foi passado através do application por AndroidViewMode
    private val guestRepository: GuestRepository = GuestRepository(context)

    private var _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    private var _guest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = _guest

    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.presence = presence
        }

        if (id == 0) {
            _saveGuest.value = guestRepository.save(guest)
        } else {
            _saveGuest.value = guestRepository.update(guest)
        }
    }

    fun load(id: Int) {
        _guest.value = guestRepository.getGuest(id)
    }

}