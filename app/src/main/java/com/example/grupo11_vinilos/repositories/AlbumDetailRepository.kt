package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.grupo11_vinilos.models.Album
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class AlbumDetailRepository(val application: Application) {
    fun refreshData(callback: (Album) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbumDetail(
            100,
            {
                callback(it)
            },
            onError
        )
    }
}