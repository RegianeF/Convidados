package com.example.convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.service.constants.DataBaseConstants

//SQLiteOpenHelper = uma classe que faz a conexão/gerenciamento com o banco de dados
//Precisa de um context, que deve ser inicializado na classe e passo para o SQLite
//SQLite precisa do context, name, factory e version e também implementar os membros
//GuesDataBaseHelper é a classe que vai ser usada para acessar  o banco de dados

class GuestDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
        //db.execSQL é do Android que chama para criar a tabela que está ali embaixo
    }

    //update serve muito bem para trabalhar com atualizações
    //sem que os usuarios cadastrados não recebam aquela atualização
    //quando for atualizar tem que trocar a versão do banco de dados, assim todos recebem a atualização nova
    //sem maiores prejuizos
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Convidados.db"

        //DataBaseConstants é uma classe que passa algum objetos para ficar mais facil aqui
        //tem um objeto dentro do outro
        private const val CREATE_TABLE_GUEST =
            //Criar a tabela
            //aqui é sintaxe que o banco de dados é capaz de interpretar:
            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
                    + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }
}