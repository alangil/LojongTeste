package com.alangil.lojongtest.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alangil.lojongtest.service.model.FraseModel

@Database(entities = [FraseModel::class], version = 1)
abstract class FraseDataBase : RoomDatabase() {

    abstract fun fraseDAO(): FraseDAO

    /**
     * Singleton
     */
    companion object {
        private lateinit var INSTANCE: FraseDataBase
        fun getDataBase(context: Context): FraseDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(FraseDataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, FraseDataBase::class.java, "fraseDB")
                        .allowMainThreadQueries()
                        .build()

                }
            }
            return INSTANCE

        }

    }
}