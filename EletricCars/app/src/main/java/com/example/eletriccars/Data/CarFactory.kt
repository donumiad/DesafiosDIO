package com.example.eletriccars.Data

import com.example.eletriccars.domain.Carro

object CarFactory {

    val List = listOf(
        Carro(
            id = 1,
            preco = "R$ 300.000,00",
            bateria = "300 Kwh",
            potencia = "200cv",
            recarga = "30 min",
            urlPhoto = "www.google.com.br"
        )

    )

}