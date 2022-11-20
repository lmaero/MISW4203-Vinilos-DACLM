package com.example.grupo11_vinilos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grupo11_vinilos.database.dao.AlbumsDao
import com.example.grupo11_vinilos.database.dao.CollectorsDao
import com.example.grupo11_vinilos.database.dao.MusiciansDao
import com.example.grupo11_vinilos.models.Album
import com.example.grupo11_vinilos.models.Collector
import com.example.grupo11_vinilos.models.Musician

@Database(
    entities = [Album::class, Collector::class, Musician::class],
    version = 1,
    exportSchema = false
)

abstract class VinylRoomDatabase : RoomDatabase() {

    abstract fun albumsDao(): AlbumsDao
    abstract fun collectorsDao(): CollectorsDao
    abstract fun musiciansDao(): MusiciansDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: VinylRoomDatabase? = null

        fun getDatabase(context: Context): VinylRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VinylRoomDatabase::class.java,
                    "vinyls_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
