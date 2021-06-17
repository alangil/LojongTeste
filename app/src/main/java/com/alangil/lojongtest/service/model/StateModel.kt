package com.alangil.lojongtest.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "State")
class StateModel {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0

        @SerializedName(value = "state")
        @ColumnInfo(name = "state")
        var state: Boolean = false



}