package com.example.eletriccars.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccars.Data.local.CarRepository
import com.example.eletriccars.R
import com.example.eletriccars.UI.adapters.CarAdapter
import com.example.eletriccars.domain.Carro

class FavoritFragment: Fragment() {

    lateinit var listaCarrosFavoritos: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCarFragment(view)
        setupList()

    }

    private fun getCarsOnLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()
        return carList
    }

    fun setupList(){
        val cars = getCarsOnLocalDb()
        val carroAdapter = CarAdapter(cars, isFavoriteScreen = true)
        listaCarrosFavoritos.apply {
            visibility = View.VISIBLE
            adapter = carroAdapter
        }
        carroAdapter.carItemLister = { carro ->
           //@TODO IMPLEMENTAR O DELETE NO DB
        }
    }

    fun setupCarFragment(view: View){
        view.apply {
            listaCarrosFavoritos = findViewById(R.id.rv_carros_favoritos)
        }
    }
}