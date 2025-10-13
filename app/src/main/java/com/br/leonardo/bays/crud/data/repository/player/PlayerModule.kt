package com.br.leonardo.bays.crud.data.repository.player

import com.br.leonardo.bays.crud.data.db.dao.PlayerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {

    @Provides
    fun providePlayerRepository(playerDao: PlayerDao): PlayerRepository {
        return PlayerRepositoryImpl(playerDao)
    }

}