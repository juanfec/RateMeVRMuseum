package com.einvopos.ratemevrmuseum.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.einvopos.ratemevrmuseum.data.daos.ExhibitDao
import com.einvopos.ratemevrmuseum.data.daos.RateDao
import com.einvopos.ratemevrmuseum.data.daos.UserDao
import com.einvopos.ratemevrmuseum.data.models.Exhibit
import com.einvopos.ratemevrmuseum.data.models.Rate
import com.einvopos.ratemevrmuseum.data.models.User

@Database(
    entities = [
        User::class,
        Exhibit::class,
        Rate::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }

    abstract fun getUserDao(): UserDao

    abstract fun getExhibitDao() : ExhibitDao

    abstract fun getRateDao() : RateDao

}