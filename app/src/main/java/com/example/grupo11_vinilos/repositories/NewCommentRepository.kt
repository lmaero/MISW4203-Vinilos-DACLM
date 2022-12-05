package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.example.grupo11_vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class NewCommentRepository(val application: Application) {
    fun refreshData(body: JSONObject, albumId: Int) {
        NetworkServiceAdapter.getInstance(application).postComment(body, albumId)
    }
}