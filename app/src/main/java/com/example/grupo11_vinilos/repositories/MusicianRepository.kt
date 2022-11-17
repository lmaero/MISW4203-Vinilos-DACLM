package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.grupo11_vinilos.models.Musician
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class MusicianRepository(val application: Application) {
    fun refreshData(callback: (List<Musician>) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getMusicians(
            {
                callback(it)
            },
            onError
        )
    }
}