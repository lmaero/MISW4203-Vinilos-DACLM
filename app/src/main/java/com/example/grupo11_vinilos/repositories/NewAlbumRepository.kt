package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.example.grupo11_vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class NewAlbumRepository(val application: Application) {
    fun refreshData(body: JSONObject) {
        NetworkServiceAdapter.getInstance(application).postAlbum((body))
    }
}