package com.example.eletriccars.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccars.R

class CalcularAltonomiaActivity: AppCompatActivity() {

    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var btnCalcular: Button
    lateinit var voltarPagina: ImageView
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_altonomia)

        setupViewe()
        setupListeners()
    }

    fun setupViewe(){
        preco = findViewById(R.id.et_precoKwh)
        kmPercorrido = findViewById(R.id.et_kmPercorrido)
        btnCalcular = findViewById(R.id.btn_calcular)
        resultado = findViewById(R.id.et_altonomia)
        voltarPagina = findViewById(R.id.iv_fecharPagina)
    }

    fun setupListeners(){
        btnCalcular.setOnClickListener{
            calcular()
        }
        voltarPagina.setOnClickListener{
            finish()
        }
    }

    private fun calcular(){
        val entradaPreco = preco.text.toString().toFloat()
        val km = kmPercorrido.text.toString().toFloat()

        val altonomia = entradaPreco / km
        resultado.text = altonomia.toString()
    }
}