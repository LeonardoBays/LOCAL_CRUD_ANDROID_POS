package com.br.leonardo.bays.crud.data.repository.match

import com.br.leonardo.bays.crud.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {

    fun getAllMatch(): Flow<List<Match>>

    fun getMatchById(id: Int): Flow<Match>

    suspend fun insertMatch(match: Match)

    suspend fun updateMatch(match: Match)

    suspend fun deleteMatch(match: Match)

}