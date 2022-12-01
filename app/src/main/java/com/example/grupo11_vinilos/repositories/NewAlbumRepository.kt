package com.example.grupo11_vinilos.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.grupo11_vinilos.database.dao.AlbumsDao
import com.example.grupo11_vinilos.models.Album
import com.example.grupo11_vinilos.models.NewAlbum
import com.example.grupo11_vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class NewAlbumRepository(val application: Application) {
    fun refreshData(body: JSONObject ) {
        //Log.d("NewAlbumRepository", name)
        NetworkServiceAdapter.getInstance(application).postAlbum((body))
    }
}