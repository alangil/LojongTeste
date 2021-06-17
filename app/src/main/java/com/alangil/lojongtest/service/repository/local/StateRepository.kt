package com.alangil.lojongtest.service.repository.local

import android.content.Context
import com.alangil.lojongtest.service.model.StateModel

class StateRepository(context: Context) {

    //Acesso ao Banco de Dados
    private val mDataBase = StateDataBase.getDataBase(context).stateDAO()

    /**
     *  Carrega State
     */

    fun get(id: Int): StateModel {
        return mDataBase.get(id)
    }

    /**
     *  Faz a listagem de todos os States
     */
    fun getAll(): List<StateModel> {
        return mDataBase.getAll()
    }

    /**
     *  Salva State
     */
    fun save(stateModel: StateModel) : Boolean{
        mDataBase.save(stateModel)
        return true
    }

    /**
     *  Atualiza state
     */
    fun update(id : Int , boolean: Boolean) : Boolean{
        mDataBase.update(id, boolean)
        return true
    }


}