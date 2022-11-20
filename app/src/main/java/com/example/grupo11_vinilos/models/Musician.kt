package com.example.grupo11_vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "musicians_table")
data class Musician(
    @PrimaryKey val musicianId: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)