package com.br.leonardo.bays.crud.data.repository.match

import com.br.leonardo.bays.crud.data.db.dao.MatchDao
import com.br.leonardo.bays.crud.data.db.entity.MatchEntity
import com.br.leonardo.bays.crud.domain.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.br.leonardo.bays.crud.domain.mapper.toDomain
import com.br.leonardo.bays.crud.domain.mapper.toEntity
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(private val dao: MatchDao) : MatchRepository {

    override fun getAllMatch(): Flow<List<Match>> {
        return dao.getAll().map { value ->
            value.map { it.toDomain() }
        }
    }

    override fun getMatchById(id: Int): Flow<Match> {
        return dao.getById(id).map { it.toDomain() }
    }

    override suspend fun insertMatch(match: Match) {
        dao.insert(match.toEntity())
    }

    override suspend fun updateMatch(match: Match) {
        dao.update(match.toEntity())
    }

    override suspend fun deleteMatch(match: Match) {
        dao.delete(match.toEntity())
    }

}