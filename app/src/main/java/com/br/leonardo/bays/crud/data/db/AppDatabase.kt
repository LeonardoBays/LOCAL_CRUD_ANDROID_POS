package com.br.leonardo.bays.crud.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.br.leonardo.bays.crud.data.db.dao.MatchDao
import com.br.leonardo.bays.crud.data.db.entity.MatchEntity

@Database(entities = [MatchEntity::class,], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}