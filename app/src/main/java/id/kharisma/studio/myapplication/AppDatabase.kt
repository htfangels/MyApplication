package id.kharisma.studio.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import id.kharisma.studio.myapplication.DatabaseDao
import id.kharisma.studio.myapplication.ModelDatabase

@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
}