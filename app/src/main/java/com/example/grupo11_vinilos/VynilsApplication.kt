package com.example.grupo11_vinilos

import android.app.Application
import com.example.grupo11_vinilos.database.VinylRoomDatabase

class VinylsApplication : Application() {
    val database by lazy { VinylRoomDatabase.getDatabase(this) }
}