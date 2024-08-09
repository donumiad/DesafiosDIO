package com.example.eletriccars.Data.local

import android.provider.BaseColumns

object CarrosContract {


    object CarEntre : BaseColumns{
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_PRECO = "preco"
        const val COLUMN_NAME_BATERIA = "bateria"
        const val COLUMN_NAME_POTENCIA = "potencia"
        const val COLUMN_NAME_RECARGA = "recarga"
        const val COLUMN_NAME_URL_PHOTO = "url_photo"
    }

    const val TABLE_CAR =
        "CREATE TABLE ${CarEntre.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${CarEntre.COLUMN_NAME_PRECO} TEXT," +
                "${CarEntre.COLUMN_NAME_BATERIA} TEXT," +
                "${CarEntre.COLUMN_NAME_POTENCIA} TEXT," +
                "${CarEntre.COLUMN_NAME_RECARGA} TEXT," +
                "${CarEntre.COLUMN_NAME_URL_PHOTO} TEXT)"

    const val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${CarEntre.TABLE_NAME}"
}