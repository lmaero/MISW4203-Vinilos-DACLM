package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.example.grupo11_vinilos.models.Collector
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class CollectorRepository(val application: Application) {
    suspend fun refreshData(): List<Collector> {
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }
}
