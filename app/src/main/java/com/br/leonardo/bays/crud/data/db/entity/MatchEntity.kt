package com.br.leonardo.bays.crud.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MATCH")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val homeTeam: String,
    val homeScore: Long,
    val awayTeam: String,
    val awayScore: Long,
    val createAt: String,
    val startAt: String,
    val endAt: String,
)