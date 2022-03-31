package com.example.convidados.service.repository

import androidx.room.*
import com.example.convidados.service.model.GuestModel

@Dao
interface GuestDao{

    @Insert //salvar dados
    fun save(guest: GuestModel) : Long //long pq é id

    @Update
    fun update(guest: GuestModel) : Int //pq é o numero de registros atualizados

    @Delete
    fun delete(guest: GuestModel)

    @Query("Select * from Guest where id = :id") //tem que ser assim :id é igual a id: Int
    fun get(id: Int) : GuestModel

    @Query("Select * from Guest")
    fun getInvited(): List<GuestModel>

    @Query("Select * from Guest where presence = 1")
    fun getPresent(): List<GuestModel>

    @Query("Select * from Guest where presence = 0")
    fun getAbsent(): List<GuestModel>
}