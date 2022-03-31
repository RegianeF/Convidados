package com.example.convidados.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidados.databinding.FragmentAllBinding
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.view.activity.GuestFromActivity
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.viewModel.GuestsViewModel

class AllGuestsFragment : Fragment() {
    //listagem:

    private lateinit var mListener: GuestListener
    private val adapter: GuestAdapter = GuestAdapter()
    private lateinit var guestsViewModel: GuestsViewModel
    private var _binding: FragmentAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        guestsViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)

        //val root é quem armazena a criacao do layout
        val root: View = binding.root

        //RecyclerView : Sempre que for necessario exibir qualquer lista Usar recyclerView
        //1 Obter um recycler
        val recycler = binding.recyclerAllGuests
        //2 Definir um layout
        recycler.layoutManager = LinearLayoutManager(context) //como vai se comportar Layout

        //3 Definir um adapter - pega o fragmente e junta com o repositorio
        //cria uma classe adapter
        recycler.adapter = adapter//cola entre elementos e dados

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFromActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                guestsViewModel.delete(id)
                guestsViewModel.load(GuestConstants.FILTER.EMPTY)
            }
        }
        adapter.attachListener(mListener)

        observer()
        /*allGuestsViewModel.load()
        * quando estava sendo chamando aqui a pagina não carregaa a lista
        * pq ela nao estava sendo destruida, então foi pra onResume
        * */
        return root
    }

    override fun onResume() {
        super.onResume()
        guestsViewModel.load(GuestConstants.FILTER.EMPTY)
        //responsavel por listar os convidados, com a lista recarregando
    }

    private fun observer() {
        guestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            adapter.updateGuests(it)
            //notifyDataSetChanged = Notify any registered observers that the data set has changed.
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}