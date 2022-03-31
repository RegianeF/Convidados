package com.example.convidados.view.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.model.GuestModel

//Classe guarda as referencias dos elementos de layout
//ViewHolder() precisa passar um itemView então faz assim mesmo:
//Lembrar de importa o View
class GuestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(guest: GuestModel){
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name
    }
}