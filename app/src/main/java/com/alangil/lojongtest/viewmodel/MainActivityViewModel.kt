package com.alangil.lojongtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alangil.lojongtest.service.model.FraseModel
import com.alangil.lojongtest.service.model.StateModel
import com.alangil.lojongtest.service.repository.local.FraseRepository
import com.alangil.lojongtest.service.repository.local.StateRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext

    // Conexão com repositorio
    private val mFraseRepository: FraseRepository = FraseRepository(mContext)
    private val mStateRepository: StateRepository = StateRepository(mContext)

    // Verificar States
    fun verificaStateBD(): Int {
        var stateBD : Int = 1
        var state = loadFraseBDState(1)
        if (state == null){
            stateBD = 0
        }
        return stateBD
    }

    fun verificaStates(): Int {
        var stateAtivo: Int = 0
        var state1 = loadState(1)
        if (state1 != null) {
            var step1IsChecked = state1.state
            if (step1IsChecked) {
                stateAtivo = 1
            }
        }
        var state2 = loadState(2)
        if (state2 != null) {
            var step2IsChecked = state2.state
            if (step2IsChecked) {
                stateAtivo = 2
            }
        }

        var state3 = loadState(3)
        if (state3 != null) {
            var step3IsChecked = state3.state
            if (step3IsChecked) {
                stateAtivo = 3
            }
        }

        var state4 = loadState(4)
        if (state4 != null) {
            var step4IsChecked = state4.state
            if (step4IsChecked) {
                stateAtivo = 4
            }
        }

        var state5 = loadState(5)
        if (state5 != null) {
            var step5IsChecked = state5.state
            if (step5IsChecked) {
                stateAtivo = 5
            }
        }
        return stateAtivo
    }


    /**
     * Carrega Estados
     */
    private var mLoadStates = MutableLiveData<List<StateModel>>()
    val loadStates: LiveData<List<StateModel>> = mLoadStates

    fun loadAllStates() {
        mLoadStates.value = mStateRepository.getAll()
    }

    fun loadState(id: Int): StateModel {
        return mStateRepository.get(id)
    }

    private var mSaveState = MutableLiveData<Boolean>()
    val saveState: LiveData<Boolean> = mSaveState

    fun saveState(state: StateModel) {
        mSaveState.value = mStateRepository.save(state)
    }

    private var mUpdateState = MutableLiveData<Boolean>()
    val updateState: LiveData<Boolean> = mUpdateState

    fun updateState(id: Int, state: Boolean) {
        mSaveState.value = mStateRepository.update(id, state)
    }


    /**
     * Carrega frase
     */
    private var mLoadFrase = MutableLiveData<FraseModel>()
    val loadFrase: LiveData<FraseModel> = mLoadFrase

    fun loadFraseBDState(id: Int): FraseModel {
        return mFraseRepository.get(id)
    }

    fun load(id: Int) {
        mLoadFrase.value = mFraseRepository.get(id)
    }
    private var mLoadFrases = MutableLiveData<List<FraseModel>>()
    val loadFrases: LiveData<List<FraseModel>> = mLoadFrases

    fun loadAll() {
        mLoadFrases.value = mFraseRepository.getAll()
    }


}



