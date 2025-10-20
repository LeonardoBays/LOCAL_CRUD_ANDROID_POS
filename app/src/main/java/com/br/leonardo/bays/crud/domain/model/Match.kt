package com.br.leonardo.bays.crud.domain.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

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
        val now = Date()
        return now.after(endAt)
    }

    fun decreaseHome(): Match {
        return this.copy(homeScore = max(this.homeScore - 1, 0))
    }

    fun decreaseAway(): Match {
        return this.copy(homeScore = max(this.awayScore - 1, 0))
    }

    fun increaseHome(): Match {
        return this.copy(homeScore = min(this.homeScore + 1, 99))
    }

    fun increaseAway(): Match {
        return this.copy(homeScore = min(this.awayScore + 1, 99))
    }

    fun statusPartida(): String {
        val now = Date()

        if (now.after(endAt)) {
            return "A partida já encerrou."
        }

        if (now.before(startAt)) {
            val dataFormatada =
                SimpleDateFormat("dd/MM 'às' HH:mm", Locale.getDefault()).format(startAt)
            return "Início previsto para $dataFormatada."
        }

        return "A partida está em andamento."
    }
}
