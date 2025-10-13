package com.br.leonardo.bays.crud.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.br.leonardo.bays.crud.data.db.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Transaction
    suspend fun insertAll(players: List<PlayerEntity>)

    @Delete
    suspend fun deleteAll(ids: List<Long>)

    @Query("SELECT * FROM `PLAYER` WHERE `matchId` = matchId")
    fun getAllFromMatch(matchId: Long): Flow<List<PlayerEntity>>
}