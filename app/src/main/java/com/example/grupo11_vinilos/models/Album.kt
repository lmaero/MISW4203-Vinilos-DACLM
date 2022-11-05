package com.example.grupo11_vinilos.models

import org.json.JSONArray

data class Album(
    val albumId: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val performers: JSONArray
)
