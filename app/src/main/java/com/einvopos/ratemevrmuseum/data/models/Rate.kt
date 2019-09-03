package com.einvopos.ratemevrmuseum.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"]),
    ForeignKey(entity = Exhibit::class,
        parentColumns = ["id"],
        childColumns = ["exhibitId"])
))
data class Rate(
    var userId: Long? = 0,
    var exhibitId: Long? = 0,
    var rating: Double? = 0.0

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    constructor() : this(0, 0,0.0)
}