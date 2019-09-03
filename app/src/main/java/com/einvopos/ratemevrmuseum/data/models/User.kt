package com.einvopos.ratemevrmuseum.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    var name: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    constructor() : this("")
}