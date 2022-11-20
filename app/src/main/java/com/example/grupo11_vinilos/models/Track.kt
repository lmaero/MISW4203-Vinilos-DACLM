package com.example.grupo11_vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks_table")
data class Track(
    @PrimaryKey val id: Int,
    var name: String,
    var duration: String
)