package com.example.eletriccars

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var btnCalcular: Button
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewe()
        setupListeners()

    }

    fun setupViewe(){
        preco = findViewById(R.id.et_precoKwh)
        kmPercorrido = findViewById(R.id.et_kmPercorrido)
        btnCalcular = findViewById(R.id.btn_calcular)
        resultado = findViewById(R.id.et_altonomia)
    }

    fun setupListeners(){
        btnCalcular.setOnClickListener{
            calcular()
        }
    }

    fun calcular(){
        val entradaPreco = preco.text.toString().toFloat()
        val km = kmPercorrido.text.toString().toFloat()

        val altonomia = entradaPreco / km
        resultado.text = altonomia.toString()
    }
}