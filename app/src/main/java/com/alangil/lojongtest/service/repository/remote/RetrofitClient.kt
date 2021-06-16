package com.alangil.lojongtest.service.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    /**
     * Retrofit para conexão com endpoint
     */

    companion object {
        private lateinit var retrofit: Retrofit
        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            if (!Companion::retrofit.isInitialized)
                retrofit = Retrofit.Builder().baseUrl("https://cat-fact.herokuapp.com/")
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }

    }


}