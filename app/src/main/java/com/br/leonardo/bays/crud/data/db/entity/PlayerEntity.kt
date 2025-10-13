package com.br.leonardo.bays.crud.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PLAYER")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val matchId: Long,
    val isHomeTeam: Boolean,
    val playerName: String,
)