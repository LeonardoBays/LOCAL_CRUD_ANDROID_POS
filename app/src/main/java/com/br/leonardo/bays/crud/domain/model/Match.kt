package com.br.leonardo.bays.crud.domain.model

import java.util.Date

data class Match(
    val id: Long,
    val homeTeam: String,
    val homeScore: Long,
    val awayTeam: String,
    val awayScore: Long,
    val createAt: Date,
    val startAt: Date,
    val endAt: Date
) {

    constructor() : this(
        id = 0,
        homeTeam = "",
        homeScore = 0,
        awayTeam = "",
        awayScore = 0,
        createAt = Date(),
        startAt = Date(),
        endAt = Date(),
    )

    fun isInProgress(): Boolean {
        val now = Date()
        return now.after(startAt) && now.before(endAt)
    }

    fun isFinished(): Boolean {
        return Date().after(endAt)
    }
}
