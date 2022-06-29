package com.manriquetavi.bakeryapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manriquetavi.bakeryapp.data.local.dao.FoodCartDao
import com.manriquetavi.bakeryapp.domain.model.FoodCart

@Database(
    entities = [FoodCart::class],
    version = 1,
    exportSchema = false
)
abstract class BakeryDatabase: RoomDatabase() {

    companion object {
        fun create(context: Context, useMemory: Boolean): BakeryDatabase {
            val dataBuilder = if(useMemory) {
                Room.inMemoryDatabaseBuilder(context, BakeryDatabase::class.java)
            } else {
                Room.databaseBuilder(context, BakeryDatabase::class.java, "test_database.db")
            }
            return dataBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun foodCartDao(): FoodCartDao

}