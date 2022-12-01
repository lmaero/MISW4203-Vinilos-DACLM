package com.example.grupo11_vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewAlbum(
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)