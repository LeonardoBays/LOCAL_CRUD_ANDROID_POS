package com.br.leonardo.bays.crud.data.repository.player

import com.br.leonardo.bays.crud.data.db.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    suspend fun insertAll(players: List<PlayerEntity>)

    suspend fun deleteAll(ids: List<Long>)

    fun getAllFromMatch(matchId: Long): Flow<List<PlayerEntity>>

}