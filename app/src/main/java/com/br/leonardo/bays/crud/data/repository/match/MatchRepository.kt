package com.br.leonardo.bays.crud.data.repository.match

import com.br.leonardo.bays.crud.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {

    fun getAllMatch(): Flow<List<Match>>

    fun getMatchById(id: Long): Flow<Match?>

    suspend fun insertMatch(match: Match)

    suspend fun updateMatch(match: Match)

    suspend fun deleteMatch(match: Match)

    suspend fun updateHomeScore(id: Long, score: Long)

    suspend fun updateAwayScore(id: Long, score: Long)

}