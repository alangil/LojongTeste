package com.alangil.lojongtest.service.repository.local

import androidx.room.*
import com.alangil.lojongtest.service.model.FraseModel


@Dao
interface FraseDAO {

    @Insert
    fun save(frase: List<FraseModel>)

    @Update
    fun update(frase: List<FraseModel>)

    @Delete
    fun delete(frase: List<FraseModel>)

    @Query("SELECT * FROM frases WHERE id = :id")
    fun get(id: Int): FraseModel

    @Query("SELECT * FROM frases")
    fun getAll(): List<FraseModel>

}