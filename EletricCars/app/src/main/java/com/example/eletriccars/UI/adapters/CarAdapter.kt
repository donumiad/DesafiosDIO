package com.example.eletriccars.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccars.R
import com.example.eletriccars.domain.Carro

class CarAdapter(private val carros: List<Carro>):
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = carros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.preco.text = carros[position].preco
        holder.potencia.text = carros[position].potencia
        holder.bateria.text = carros[position].bateria
        holder.recarga.text = carros[position].recarga
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView
        val bateria: TextView
        val potencia: TextView
        val recarga: TextView
        init {
            preco = view.findViewById(R.id.tv_precoValor)
            bateria = view.findViewById(R.id.tv_bateriaValor)
            potencia = view.findViewById(R.id.tv_potenciaValor)
            recarga = view.findViewById(R.id.tv_recargaValor)
        }

    }

}
