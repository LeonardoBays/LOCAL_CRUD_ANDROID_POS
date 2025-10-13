package com.br.leonardo.bays.crud.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.br.leonardo.bays.crud.data.db.dao.MatchDao
import com.br.leonardo.bays.crud.data.db.entity.MatchEntity
import com.br.leonardo.bays.crud.data.db.entity.PlayerEntity

@Database(entities = [MatchEntity::class, PlayerEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}