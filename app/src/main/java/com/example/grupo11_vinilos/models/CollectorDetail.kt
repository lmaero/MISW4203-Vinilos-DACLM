package com.example.grupo11_vinilos.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collectors_detail_table")
data class CollectorDetail(
    @PrimaryKey val collectorId: Int,
    val name: String,
    val telephone: String,
    val email: String,
)
