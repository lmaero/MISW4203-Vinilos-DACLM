package com.example.grupo11_vinilos.ui

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.NewAlbumFragmentBinding
import com.example.grupo11_vinilos.models.NewAlbum
import org.json.JSONObject

class VolleyBroker constructor(context: Context){

    val instance: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object{
        const val BASE_URL= "https://grupo11-vynils-back.herokuapp.com/"

        fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
            return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
        }
    }
}