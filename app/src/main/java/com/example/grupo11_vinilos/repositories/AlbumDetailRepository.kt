package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.grupo11_vinilos.models.AlbumDetail
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class AlbumDetailRepository(val application: Application) {
    fun refreshData(albumId: Int, callback: (AlbumDetail) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbumDetail(
            albumId,
            {
                callback(it)
            },
            onError
        )
    }
}