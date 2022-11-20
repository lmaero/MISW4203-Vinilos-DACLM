package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.grupo11_vinilos.models.AlbumDetail
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class AlbumDetailRepository(val application: Application) {
    suspend fun refreshData(albumId: Int): AlbumDetail? {
        return NetworkServiceAdapter.getInstance(application).getAlbumDetail(albumId)
    }
}