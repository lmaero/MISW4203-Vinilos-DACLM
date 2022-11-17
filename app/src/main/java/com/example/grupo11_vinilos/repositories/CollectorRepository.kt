package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.grupo11_vinilos.models.Collector
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class CollectorRepository(val application: Application) {
    fun refreshData(callback: (List<Collector>) -> Unit, onError: (VolleyError) -> Unit) {
        NetworkServiceAdapter.getInstance(application).getCollectors(
            {
                callback(it)
            },
            onError
        )
    }
}
