package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewModel.GuestFormViewModel
import com.example.convidados.databinding.ActivityGuestFromBinding

class GuestFromActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestFromBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFromBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //passa para viewModel
        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()

    }

    private fun setListeners() {

        binding.buttonSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked //um boolean que verifica se = true
            viewModel.save(name, presence) //save vem da função do viewModel
        }
    }

    private fun observe() {
        viewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }
}