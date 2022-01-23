package com.ari.mvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ari.mvvm.data.database.dao.QuoteDao
import com.ari.mvvm.data.database.entities.QuoteEntity

@Database(
    entities = [QuoteEntity::class],
    version = 1
)
abstract class QuoteDataBase: RoomDatabase() {

    abstract fun getDao(): QuoteDao

}