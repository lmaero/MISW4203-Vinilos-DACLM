package com.example.grupo11_vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums_detail_table")
data class AlbumDetail(
    @PrimaryKey val albumId: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    var tracks: MutableList<Track> = mutableListOf(),
    var comments: MutableList<Comment> = mutableListOf(),
    var performers: String
)