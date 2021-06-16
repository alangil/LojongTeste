package com.alangil.lojongtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alangil.lojongtest.service.model.FraseModel
import com.alangil.lojongtest.service.repository.local.FraseRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext

    // Conexão com repositorio
    private val mFraseRepository: FraseRepository = FraseRepository(mContext)


    /**
     * Carrega frase
     */
    private var mLoadFrase = MutableLiveData<FraseModel>()
    val loadFrase: LiveData<FraseModel> = mLoadFrase

    fun load(id: Int) {
        mLoadFrase.value = mFraseRepository.get(id)
    }

}



