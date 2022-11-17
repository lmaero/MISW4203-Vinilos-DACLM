package com.example.grupo11_vinilos.models

data class MusicianDetail(
    val musicianId: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: MutableList<Album> = mutableListOf<Album>()
)


