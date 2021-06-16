package com.alangil.lojongtest.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//Entidade

@Entity(tableName = "Frases")
class FraseModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @SerializedName(value = "text")
    @ColumnInfo(name = "text")
    var text: String = ""

}