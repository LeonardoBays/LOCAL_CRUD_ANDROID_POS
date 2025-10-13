package com.br.leonardo.bays.crud.data.repository.player

import com.br.leonardo.bays.crud.data.db.dao.PlayerDao
import com.br.leonardo.bays.crud.data.db.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.collections.chunked

class PlayerRepositoryImpl @Inject constructor(private val dao: PlayerDao) : PlayerRepository {
    override suspend fun insertAll(players: List<PlayerEntity>) {
        players.chunked(500).forEach { chunk ->
            dao.insertAll(chunk)
        }
    }

    override suspend fun deleteAll(ids: List<Long>) {
        ids.chunked(500).forEach { chunk ->
            dao.deleteAll(chunk)
        }
    }

    override fun getAllFromMatch(matchId: Long): Flow<List<PlayerEntity>> {
        return dao.getAllFromMatch(matchId)
    }


}