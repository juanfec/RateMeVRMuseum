package com.einvopos.ratemevrmuseum.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.einvopos.ratemevrmuseum.data.models.Exhibit

@Dao
interface ExhibitDao {
    @Query("SELECT * FROM exhibit")
    fun getExhibits(): LiveData<MutableList<Exhibit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(item: List<Exhibit>)

    @Query("SELECT * FROM exhibit WHERE id = :exhibit")
    fun findById(exhibit : Long): Exhibit
}