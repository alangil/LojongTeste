package com.alangil.lojongtest.service.repository.remote

import com.alangil.lojongtest.service.model.FraseModel
import retrofit2.Call
import retrofit2.http.GET

interface FactsService {

    // Obter Endpoint "facts"

    @GET("facts")
    fun list(): Call<List<FraseModel>>

}