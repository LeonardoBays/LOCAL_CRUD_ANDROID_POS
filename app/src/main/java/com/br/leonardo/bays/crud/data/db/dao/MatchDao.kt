package com.br.leonardo.bays.crud.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.br.leonardo.bays.crud.data.db.entity.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {

    @Insert
    suspend fun insert(match: MatchEntity)

    @Update
    suspend fun update(match: MatchEntity)

    @Delete
    suspend fun delete(match: MatchEntity)

    @Query("SELECT * FROM `MATCH` ORDER BY startAt desc, endAt desc")
    fun getAll(): Flow<List<MatchEntity>>

    @Query("SELECT * FROM `MATCH` WHERE id = :id")
    fun getById(id: Int): Flow<MatchEntity>
}