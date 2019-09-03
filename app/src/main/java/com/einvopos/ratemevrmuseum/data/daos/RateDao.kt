package com.einvopos.ratemevrmuseum.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.einvopos.ratemevrmuseum.data.models.Exhibit
import com.einvopos.ratemevrmuseum.data.models.Rate

@Dao
interface RateDao {
    @Query("SELECT * FROM rate WHERE exhibitId = :idExhibit")
    fun getRate(idExhibit: Long): Rate?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(rate: List<Rate>)
}