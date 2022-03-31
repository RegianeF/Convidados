package com.example.convidados.service.constants

class DataBaseConstants {
//criando uma consta com id, name, presence é melhor para nao errar.
    object GUEST{
        const val TABLE_NAME = "Guest"

        object COLUMNS{
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}