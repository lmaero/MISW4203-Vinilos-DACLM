package com.example.grupo11_vinilos.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.grupo11_vinilos.models.Album

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM albums_table")
    fun getAlbums(): List<Album>
}