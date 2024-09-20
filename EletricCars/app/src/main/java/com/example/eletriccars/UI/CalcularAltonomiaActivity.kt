package com.example.eletriccars.UI

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccars.R
import com.example.eletriccars.databinding.ActivityCalcularAltonomiaBinding

class CalcularAltonomiaActivity: AppCompatActivity() {

    private val binding by lazy { ActivityCalcularAltonomiaBinding.inflate(layoutInflater) }

    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var btnCalcular: Button
    lateinit var voltarPagina: ImageView
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewe()
        setupListeners()
        castingResult()
    }

    private fun castingResult() {
        val valorCalculado = getSharedPref()
        resultado.text = valorCalculado.toString()

    }

    fun setupViewe(){
        preco = findViewById(R.id.et_precoKwh)
        kmPercorrido = findViewById(R.id.et_kmPercorrido)
        btnCalcular = findViewById(R.id.btn_calcularAutonomia)
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
        saveSharedPref(altonomia)
    }

    fun saveSharedPref(resultado : Float){
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putFloat(getString(R.string.saved_calc), resultado)
            apply()
        }
    }

    fun getSharedPref() : Float{
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }
}