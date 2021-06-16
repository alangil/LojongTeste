package com.alangil.lojongtest.service.repository.local

import android.content.Context
import android.widget.Toast
import com.alangil.lojongtest.service.model.FraseModel
import com.alangil.lojongtest.service.repository.remote.FactsService
import com.alangil.lojongtest.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FraseRepository(context: Context) {

    //Acesso ao Banco de Dados
    private val mDataBase = FraseDataBase.getDataBase(context).fraseDAO()
    private val context = context

    /**
     * Utilizar Retrofit
     */

    // Chamada para salvar dados da API -> BD
    private val remote = RetrofitClient.createService(FactsService::class.java)
    val call: Call<List<FraseModel>> = remote.list()
    val response = call.enqueue(object : Callback<List<FraseModel>> {
        override fun onFailure(call: Call<List<FraseModel>>, t: Throwable) {
            Toast.makeText(context, "Erro ao carregar API", Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(
            call: Call<List<FraseModel>>,
            response: Response<List<FraseModel>>
        ) {
            if (response.code() > 0) {
                response.body()
                response.body()?.let { mDataBase.save(it) }
            }
        }

    })

    // Chamada para atualizar dados da API -> BD
    val call2: Call<List<FraseModel>> = remote.list()
    val responseUp = call2.enqueue(object : Callback<List<FraseModel>> {
        override fun onFailure(call: Call<List<FraseModel>>, t: Throwable) {
            Toast.makeText(context, "Erro ao carregar API", Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(
            call: Call<List<FraseModel>>,
            response: Response<List<FraseModel>>
        ) {
            if (response.code() > 0) {
                response.body()
                response.body()?.let { mDataBase.update(it) }
            }
        }

    })


    /**
     *  Carrega frase
     */

    fun get(id: Int): FraseModel {
        return mDataBase.get(id)
    }

    /**
     *  Faz a listagem de todas as frases
     */
    fun getAll(): List<FraseModel> {
        return mDataBase.getAll()
    }


}
