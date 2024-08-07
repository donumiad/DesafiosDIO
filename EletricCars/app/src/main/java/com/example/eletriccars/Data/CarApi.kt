package com.example.eletriccars.Data

import com.example.eletriccars.domain.Carro
import retrofit2.Call
import retrofit2.http.GET

interface CarApi {

    @GET("cars.json")
    fun getAllCars() : Call<List<Carro>>
}