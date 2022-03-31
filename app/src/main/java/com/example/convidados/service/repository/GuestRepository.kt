package com.example.convidados.service.repository

import android.content.Context
import com.example.convidados.service.model.GuestModel

//GuesDataBaseHelper é a classe que vai ser usada para acessar  o banco de dados aqui no repositório

class GuestRepository  constructor(context: Context) {

    //acesso ao banco de dados
    private val mDataBase = GuestDataBase.getDataBase(context).guestDao()

    fun getGuest(id: Int): GuestModel { //pegar um dado especifico de 1 convidado
       return mDataBase.get(id)
    }

    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    fun getPresence(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDataBase.getAbsent()
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel){
        return mDataBase.delete(guest)
    }
}