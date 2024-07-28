package com.example.eletriccars.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccars.Data.CarFactory
import com.example.eletriccars.R
import com.example.eletriccars.UI.adapters.CarAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var btnCalcularmain: Button
    lateinit var listaCarros: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewemain()
        setupListenersmain()
        setupList()
    }

    fun setupViewemain(){
        btnCalcularmain = findViewById(R.id.btn_calcularmain)
        listaCarros = findViewById(R.id.rv_carros)
    }

    fun setupList(){

        val adapter = CarAdapter(CarFactory.List)
        listaCarros.adapter = adapter
    }

    fun setupListenersmain(){
        btnCalcularmain.setOnClickListener{
            startActivity(Intent(this, CalcularAltonomiaActivity:: class.java))
        }
    }


}