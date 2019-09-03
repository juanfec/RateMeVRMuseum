package com.einvopos.ratemevrmuseum.data.models

import android.accounts.AuthenticatorDescription
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class Exhibit(
    var name: String,
    var year: Int,
    var description: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    constructor() : this("", 0, "")
}