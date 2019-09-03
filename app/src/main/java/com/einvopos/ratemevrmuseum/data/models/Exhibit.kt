package com.einvopos.ratemevrmuseum.data.models

import android.accounts.AuthenticatorDescription
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exhibit")
data class Exhibit(
    var name: String,
    var year: Int,
    var description: String,
    var imgUrl: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    constructor() : this("", 0, "","")
}