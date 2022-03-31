package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.constants.DataBaseConstants
import com.example.convidados.service.model.GuestModel

//GuesDataBaseHelper é a classe que vai ser usada para acessar  o banco de dados aqui no repositório

class GuestRepository private constructor(context: Context) {

    //singleton = tem metodo estatico que apenas ele pode te da uma instancia da classe

    //com essa variavavel pega o banco de dados lá da sua classe
    private var guestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    //metodo estatico que será responsavel por retorna a instancia da classe que pode ser só uma
    companion object {
        private lateinit var repository: GuestRepository
        //essa variavel vai guardar a instancia da classe

        //atraves dessa função que retorna o GuestRepository para ser instanciada
        fun getInstance(context: Context): GuestRepository {
            //pq ali em cima tem o lateinit é preciso fazer assim:
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    //singleton

    //inserir é mais fácil pq não precisa de condição, é só inserir
    fun save(guest: GuestModel): Boolean {
        return try {

            val db = guestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()
            //adicionar o valor pra coluna nome E  valor do nome
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            //esses valores vem lá de GuestModel

            //insert é para inserir precisa ter uma contentValue!
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            //DataBaseConstants é o nome da tabela
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }


    //metodos para pegar - ler - todos os convidados
    //igual ao getGuest, duvida olhar la

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()

        return try {
            val db = guestDataBaseHelper.readableDatabase
            //condições

            //quais colunas queremos trabalhar? all
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            //query é pra consultar um valor
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            //precisa verificar se não tem valor nulo:
            //.count . move é tudo sintaxe do android
            if (cursor != null && cursor.count > 0) { //se retornou algum valor
                //enquanto o cursor não estiver vazio faça: - enquanto existir valores no curso faça
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)) //isso aqui não ta errado/ só IGNORAR
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)) //isso aqui não ta errado/ só IGNORAR
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    //criando o guest, com o que deve ser retornado
                    val guest = GuestModel(name, presence, id)

                    list.add(guest) //como o retorno é uma lista, pega o guest e adiciona na lista para retorno
                }

            }

            cursor?.close() //sempre liberar o cursor da memória

            //retorno
            list
        } catch (e: Exception) {
            e.printStackTrace()
            list
        }
    }

    fun getPresence(): List<GuestModel> { //com rawQuery
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = guestDataBaseHelper.readableDatabase
            //condições

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)
            //CUIDAR ao escrever
            //selecione id, nome, presenca de Guest - constante que o nome é Guest, onde presence seja 1

            //precisa verificar se não tem valor nulo:
            //.count . move é tudo sintaxe do android
            if (cursor != null && cursor.count > 0) { //se retornou algum valor
                //enquanto o cursor não estiver vazio faça: - enquanto existir valores no curso faça
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)) //isso aqui não ta errado/ só IGNORAR
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)) //isso aqui não ta errado/ só IGNORAR
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    //criando o guest, com o que deve ser retornado
                    val guest = GuestModel(name, presence, id)

                    list.add(guest) //como o retorno é uma lista, pega o guest e adiciona na lista para retorno
                }
            }

            cursor?.close() //sempre liberar o cursor da memória

            //retorno
            list
        } catch (e: Exception) {
            e.printStackTrace()
           return list
        }
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = mutableListOf()
        return try {
            val db = guestDataBaseHelper.readableDatabase
            //condições

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)
            //CUIDAR ao escrever
            //selecione id, nome, presenca de Guest - constante que o nome é Guest, onde presence seja 1

            //precisa verificar se não tem valor nulo:
            //.count . move é tudo sintaxe do android
            if (cursor != null && cursor.count > 0) { //se retornou algum valor
                //enquanto o cursor não estiver vazio faça: - enquanto existir valores no curso faça
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)) //isso aqui não ta errado/ só IGNORAR
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)) //isso aqui não ta errado/ só IGNORAR
                    val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    //criando o guest, com o que deve ser retornado
                    val guest = GuestModel(name, presence, id)

                    list.add(guest) //como o retorno é uma lista, pega o guest e adiciona na lista para retorno
                }
            }

            cursor?.close() //sempre liberar o cursor da memória

            //retorno
            list
        } catch (e: Exception) {
            e.printStackTrace()
            return list
        }
    }


    fun getGuest(id: Int): GuestModel? { //pegar um dado especifico de um convidado

        var guest: GuestModel? = null

        return try {
            val db = guestDataBaseHelper.readableDatabase
            //condições

            //array de strings que são os nomes das colunas que deseja retornar : projection
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val selection =
                DataBaseConstants.GUEST.COLUMNS.ID + " = ? " //se na tabela, a partir da coluna, id é igual ?(args)
            val args = arrayOf(id.toString()) //paramentro que vem ali de cima
            //selection é o criterio de seleção, os dados de id são iguais aos passados pelo paramentro + args

            //query é pra consultar um valor
            //tem o rawQuery, só não é seguro
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            //precisa verificar se não tem valor nulo:
            //.count . move é tudo sintaxe do android
            if (cursor != null && cursor.count > 0) { //se não for nulo e maior que 0,
                //ou seja se retornou algum valor
                cursor.moveToFirst() //até aqui ele só verifica por linhas que move para primeiro
                //pq o cursor vai passando na lista.

                //tentar pegar: Ele espera um index da coluna, para isso fazer (.getColumIndex
                // () <- como chama o nome da coluna? e colocar )
                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)) //isso aqui não ta errado/ só IGNORAR
                //val name é pra pegar o valor do nome/nome é uma string

                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)
                //presence é um boolean e não tem getBoolean então foi feito assim ==1 pq se for igual a 1 o valor se torna true

                //criando o guest, com o que deve ser retornado
                guest = GuestModel(name, presence, id)
            }

            cursor?.close() //sempre liberar o cursor da memória

            //retorno
            guest
        } catch (e: Exception) {
            e.printStackTrace()
            guest
        }
    }

    //CRUD: CREATE READ UPDATE DELETE
    //guest vem da da data class que ta sendo usada para criar um atributo
    //Create

    //atualização precisa de um critério, uma condição
    fun update(guest: GuestModel): Boolean {
        //Atualização está acontecendo a partir da condição se o ID da coluna, for igual ao id do convidado que é guest: Guest Model
        //olhar em save é parecido
        return try {
            val db = guestDataBaseHelper.writableDatabase
            val contentValues = ContentValues()
            //adicionar o valor pra coluna nome E  valor do nome
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            //condições
            val selection =
                DataBaseConstants.GUEST.COLUMNS.ID + " = ? " //se na tabela, a partir da coluna, id é igual ?(args)
            val args = arrayOf(guest.id.toString()) //paramentro que vem ali de cima

            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun delete(id: Int): Boolean { //id foi colocado aqui
        //olhar em save é parecido
        return try {
            val db = guestDataBaseHelper.writableDatabase

            //condições
            val selection =
                DataBaseConstants.GUEST.COLUMNS.ID + " = ? " //se na tabela, a partir da coluna, id é igual ?(args)
            val args = arrayOf(id.toString()) //paramentro que vem ali de cima

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    }
}