package com.br.leonardo.bays.crud.data.repository.match

import com.br.leonardo.bays.crud.data.db.dao.MatchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MatchModule {

    @Provides
    fun provideMatchRepository(matchDao: MatchDao): MatchRepository {
        return MatchRepositoryImpl(matchDao)
    }

}