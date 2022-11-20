package com.example.grupo11_vinilos.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.grupo11_vinilos.models.Collector

@Dao
interface CollectorsDao {
    @Query("SELECT * FROM collectors_table")
    fun getCollectors(): List<Collector>
}