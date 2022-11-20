package com.example.grupo11_vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.grupo11_vinilos.database.dao.MusiciansDao
import com.example.grupo11_vinilos.models.Musician
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class MusicianRepository(val application: Application, private val musiciansDao: MusiciansDao) {
    suspend fun refreshData(): List<Musician> {
        val cached = musiciansDao.getMusicians()
        return cached.ifEmpty {
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getMusicians()
        }
    }
}