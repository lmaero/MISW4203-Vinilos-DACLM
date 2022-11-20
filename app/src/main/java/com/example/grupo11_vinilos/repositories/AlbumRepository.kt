package com.example.grupo11_vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.grupo11_vinilos.database.dao.AlbumsDao
import com.example.grupo11_vinilos.models.Album
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class AlbumRepository(val application: Application, private val collectorsDao: AlbumsDao) {
    suspend fun refreshData(): List<Album> {
        val cached = collectorsDao.getAlbums()
        return cached.ifEmpty {
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                emptyList()
            } else NetworkServiceAdapter.getInstance(application).getAlbums()
        }
    }
}