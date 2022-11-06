package com.example.grupo11_vinilos.models

data class AlbumDetail(
    val albumId: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    var tracks: MutableList<Track> = mutableListOf<Track>(),
    var comments: MutableList<Comment> = mutableListOf<Comment>(),
    var performers: String
)