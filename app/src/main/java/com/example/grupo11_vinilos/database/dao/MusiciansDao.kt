package com.example.grupo11_vinilos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.grupo11_vinilos.models.Musician

@Dao
interface MusiciansDao {
    @Query("SELECT * FROM musicians_table")
    fun getMusicians(): List<Musician>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(musician: Musician)

    @Query("DELETE FROM musicians_table")
    suspend fun deleteAll()
}