package com.example.grupo11_vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.grupo11_vinilos.database.dao.CollectorsDao
import com.example.grupo11_vinilos.models.Collector
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class CollectorRepository(val application: Application, private val collectorsDao: CollectorsDao) {
    suspend fun refreshData(): List<Collector> {
        val cached = collectorsDao.getCollectors()
        return cached.ifEmpty {
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getCollectors()
        }
    }
}
