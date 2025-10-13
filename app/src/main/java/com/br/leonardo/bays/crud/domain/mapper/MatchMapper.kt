package com.br.leonardo.bays.crud.domain.mapper

import com.br.leonardo.bays.crud.data.db.entity.MatchEntity
import com.br.leonardo.bays.crud.domain.model.Match
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun MatchEntity.toDomain(): Match {
    return Match(
        id = id,
        homeTeam = homeTeam,
        homeScore = homeScore,
        awayTeam = awayTeam,
        awayScore = awayScore,
        createAt = stringToDate(createAt),
        startAt = stringToDate(startAt),
        endAt = stringToDate(endAt)
    )
}

fun Match.toEntity(): MatchEntity {
    return MatchEntity(
        id = id,
        homeTeam = homeTeam,
        homeScore = homeScore,
        awayTeam = awayTeam,
        awayScore = awayScore,
        createAt = dateToString(createAt),
        startAt = dateToString(startAt),
        endAt = dateToString(endAt),
    )
}

private fun stringToDate(date: String): Date {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.parse(date) ?: Date()
}

private fun dateToString(date: Date): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.format(date)
}

