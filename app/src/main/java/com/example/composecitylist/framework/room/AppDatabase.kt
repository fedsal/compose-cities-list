package com.example.composecitylist.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composecitylist.framework.room.model.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "cities_database"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}