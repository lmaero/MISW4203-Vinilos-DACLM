package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.grupo11_vinilos.models.MusicianDetail
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class MusicianDetailRepository(val application: Application) {
    fun refreshData(musicianId: Int, callback: (MusicianDetail) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getMusicianDetail(
            musicianId,
            {
                callback(it)
            },
            onError
        )
    }
}