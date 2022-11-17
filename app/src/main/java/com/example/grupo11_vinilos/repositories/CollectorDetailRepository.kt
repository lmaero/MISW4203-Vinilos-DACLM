package com.example.grupo11_vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.grupo11_vinilos.models.CollectorDetail
import com.example.grupo11_vinilos.network.NetworkServiceAdapter

class CollectorDetailRepository(val application: Application) {
    fun refreshData(
        collectorId: Int,
        callback: (CollectorDetail) -> Unit,
        onError: (VolleyError) -> Unit
    ) {
        NetworkServiceAdapter.getInstance(application).getCollectorDetail(
            collectorId,
            {
                callback(it)
            },
            onError
        )
    }
}