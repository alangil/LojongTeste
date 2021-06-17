package com.alangil.lojongtest.service.repository.local

import androidx.room.*
import com.alangil.lojongtest.service.model.StateModel

@Dao
interface StateDAO {

    @Insert
    fun save(state: StateModel)

    @Query("UPDATE state SET state = :state WHERE id = :id")
    fun update(id : Int ,state: Boolean)

    @Delete
    fun delete(state: StateModel)

    @Query("SELECT * FROM state WHERE id = :id")
    fun get(id: Int): StateModel

    @Query("SELECT * FROM state")
    fun getAll(): List<StateModel>

}