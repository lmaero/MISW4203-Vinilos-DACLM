package com.example.grupo11_vinilos.models

data class AlbumDetail(
    val albumId: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val tracks: String,
    val performers: String,
    val comments: String
)