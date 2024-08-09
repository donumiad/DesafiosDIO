package com.example.eletriccars.Data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import com.example.eletriccars.Data.local.CarrosContract.CarEntre.COLUMN_NAME_BATERIA
import com.example.eletriccars.Data.local.CarrosContract.CarEntre.COLUMN_NAME_POTENCIA
import com.example.eletriccars.Data.local.CarrosContract.CarEntre.COLUMN_NAME_PRECO
import com.example.eletriccars.Data.local.CarrosContract.CarEntre.COLUMN_NAME_RECARGA
import com.example.eletriccars.Data.local.CarrosContract.CarEntre.COLUMN_NAME_URL_PHOTO
import com.example.eletriccars.Data.local.CarrosContract.CarEntre.TABLE_NAME
import com.example.eletriccars.domain.Carro

class CarRepository(private val context: Context) {

    fun save(carro: Carro) : Boolean{
        var isSaved = false
        try {
            val dbHelper = CArDbHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(CarrosContract.CarEntre.COLUMN_NAME_PRECO, carro.preco)
                put(CarrosContract.CarEntre.COLUMN_NAME_BATERIA, carro.bateria)
                put(CarrosContract.CarEntre.COLUMN_NAME_POTENCIA, carro.potencia)
                put(CarrosContract.CarEntre.COLUMN_NAME_RECARGA, carro.recarga)
                put(CarrosContract.CarEntre.COLUMN_NAME_URL_PHOTO, carro.urlPhoto)

            }

            val inserted = db?.insert(TABLE_NAME,null, values)
            if (inserted != null){
                isSaved = true
            }

        } catch (ex: Exception){
            ex?.message?.let { Log.e("Erro ao inserir dados", it) }
        }
        return isSaved
    }

    fun findCarVyId(id: Int){
        val dbHelper = CArDbHelper(context)

        val db = dbHelper.readableDatabase

        //LISTAGEM DAS COLUNAS NO RESULTADO DA QUERY
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO)

        val filter = "${BaseColumns._ID} = ?"
        val filterValues = arrayOf(id.toString())

        val cursor = db.query(
            CarrosContract.CarEntre.TABLE_NAME, //NOME DA TABLEA
            columns,
            filter,
            filterValues,
            null,
            null,
            null
        )
    }


}