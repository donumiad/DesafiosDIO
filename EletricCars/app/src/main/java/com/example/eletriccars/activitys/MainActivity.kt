package com.example.eletriccars.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccars.R

class MainActivity : AppCompatActivity() {
    lateinit var btnCalcularmain: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewemain()
        setupListenersmain()
    }

    fun setupViewemain(){
        btnCalcularmain = findViewById(R.id.btn_calcularmain)
    }

    fun setupListenersmain(){
        btnCalcularmain.setOnClickListener{
            startActivity(Intent(this, CalcularAltonomiaActivity:: class.java))
        }
    }


}