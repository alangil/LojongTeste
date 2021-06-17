package com.alangil.lojongtest.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alangil.lojongtest.service.model.StateModel

@Database(entities = [StateModel::class], version = 1)
abstract class StateDataBase : RoomDatabase() {

    abstract fun stateDAO(): StateDAO

    /**
     * Singleton
     */
    companion object {
        private lateinit var INSTANCE: StateDataBase
        fun getDataBase(context: Context): StateDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(StateDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, StateDataBase::class.java, "stateDB")
                        .allowMainThreadQueries()
                        .build()

                }
            }
            return INSTANCE

        }

    }
}