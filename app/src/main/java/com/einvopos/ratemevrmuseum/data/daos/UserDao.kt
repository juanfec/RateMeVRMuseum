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

    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<MutableList<User>>
}