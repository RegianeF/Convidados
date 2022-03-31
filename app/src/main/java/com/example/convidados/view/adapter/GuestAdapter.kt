package com.example.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.model.GuestModel
import com.example.convidados.view.holder.GuestViewHolder

//preciso extender RecyclerView.Adapter<>  e adicionar seus metodos e ()
//<> pede um view holder, então foi criado uma classe GuestViewHolder
class GuestAdapter: RecyclerView.Adapter<GuestViewHolder>() {


    private var guestList: List<GuestModel> = arrayListOf()

    //cria o layout da listagem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item)
    }

    //quantidade de registros - convidados, passando a lista de convidados
    override fun getItemCount(): Int {
        return guestList.count()
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        //acessa posição da lista
        holder.bind(guestList[position])
        //recicla ao inves de ficar criando
    }

    fun updateGuests(list: List<GuestModel>){
        guestList = list
        notifyDataSetChanged()
    }

}