package com.example.grupo11_vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "musicians_detail_table")
data class MusicianDetail(
    @PrimaryKey val musicianId: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: MutableList<Album> = mutableListOf()
)


