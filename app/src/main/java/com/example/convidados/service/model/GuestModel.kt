package com.example.convidados.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//entidade - ela quem vai criar as colunas

@Entity(tableName = "Guest")
class GuestModel{
    @PrimaryKey(autoGenerate = true) //vai ter id que coloca na lista
    @ColumnInfo(name = "id") //nome da coluna
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = true
}
