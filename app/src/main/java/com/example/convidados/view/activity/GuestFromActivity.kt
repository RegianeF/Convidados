package com.example.convidados.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewModel.GuestFormViewModel
import com.example.convidados.databinding.ActivityGuestFromBinding
import com.example.convidados.service.constants.GuestConstants

class GuestFromActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestFromBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFromBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //passa para viewModel
        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        observe()
        setListeners()
        loadData()

        binding.radioPresence.isChecked = true
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId  = bundle.getInt(GuestConstants.GUESTID)
            viewModel.load(guestId)
        }
    }

    private fun setListeners() {

        binding.buttonSave.setOnClickListener {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked //um boolean que verifica se = true

            viewModel.save(guestId,name, presence) //save vem da função do viewModel
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

        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresence.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })

    }
}