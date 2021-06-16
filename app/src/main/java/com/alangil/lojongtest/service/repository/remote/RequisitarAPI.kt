package com.alangil.lojongtest.service.repository.remote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.alangil.lojongtest.service.repository.local.FraseRepository
import com.alangil.lojongtest.viewmodel.MainActivityViewModel

class RequisitarAPI() : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        /**
         * Atividades a serem realizadas pelo BreadCast
         */

        val mContext = context
        val mFraseRepository: FraseRepository = FraseRepository(mContext)
        if (mFraseRepository.getAll() == null) mFraseRepository.response
        else mFraseRepository.responseUp


    }
}