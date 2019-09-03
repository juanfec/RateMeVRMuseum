package com.einvopos.ratemevrmuseum.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.einvopos.ratemevrmuseum.data.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(user: List<User>)

    @Query("SELECT * FROM User WHERE id = :userId")
    fun findById(userId : String): LiveData<User>

    @Query("SELECT * FROM User")
    fun getItems(): LiveData<MutableList<User>>
}