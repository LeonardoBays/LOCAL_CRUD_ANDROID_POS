package com.br.leonardo.bays.crud.viewmodel.manager_match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.leonardo.bays.crud.data.repository.match.MatchRepository
import com.br.leonardo.bays.crud.domain.model.Match
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class ManagerMatchViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _match = MutableStateFlow<Match>(Match())
    val match: StateFlow<Match> = _match

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _homeTeamName = MutableStateFlow<String>("")
    val homeTeamName: StateFlow<String> = _homeTeamName

    private val _awayTeamName = MutableStateFlow<String>("")
    val awayTeamName: StateFlow<String> = _awayTeamName

    private val _dtInicial = MutableStateFlow<Long?>(null)
    val dtInicial: StateFlow<Long?> = _dtInicial

    private val _dtFinal = MutableStateFlow<Long?>(null)
    val dtFinal: StateFlow<Long?> = _dtFinal

    private val _hrInicial = MutableStateFlow<LocalTime?>(null)
    val hrInicial: StateFlow<LocalTime?> = _hrInicial

    private val _hrFinal = MutableStateFlow<LocalTime?>(null)
    val hrFinal: StateFlow<LocalTime?> = _hrFinal


    private val _showDtInicialPicker = MutableStateFlow<Boolean>(false)
    val showDtInicialPicker: StateFlow<Boolean> = _showDtInicialPicker

    private val _showDtFinalPicker = MutableStateFlow<Boolean>(false)
    val showDtFinalPicker: StateFlow<Boolean> = _showDtFinalPicker

    private val _showHrInicialPicker = MutableStateFlow<Boolean>(false)
    val showHrInicialPicker: StateFlow<Boolean> = _showHrInicialPicker

    private val _showHrFinalPicker = MutableStateFlow<Boolean>(false)
    val showHrFinalPicker: StateFlow<Boolean> = _showHrFinalPicker

    private val _isSaving = MutableStateFlow<Boolean>(false)
    val isSaving: StateFlow<Boolean> = _isSaving

    val dateLabelInicial: String
        get() {
            val dataMillis = _dtInicial.value
            val hora = _hrInicial.value

            if (dataMillis == null && hora == null) {
                return "Data inicial"
            }

            return formatarDataEHora(dataMillis, hora)
        }

    val dateLabelFinal: String
        get() {
            val dataMillis = _dtFinal.value
            val hora = _hrFinal.value

            if (dataMillis == null && hora == null) {
                return "Data final"
            }

            return formatarDataEHora(dataMillis, hora)
        }

    fun loadMatch(matchId: Long?) {
        try {
            matchId?.let {
                viewModelScope.launch {
                    matchRepository.getMatchById(matchId).collect { match ->
                        match?.let {
                            _match.value = it

                            _homeTeamName.value = it.homeTeam
                            _awayTeamName.value = it.awayTeam

                            val startAtLong = it.startAt.time
                            val endAtLong = it.endAt.time

                            _dtInicial.value = startAtLong
                            _dtFinal.value = endAtLong

                            val calendar = Calendar.getInstance()

                            calendar.timeInMillis = startAtLong
                            val horaInicial = calendar.get(Calendar.HOUR_OF_DAY)
                            val minutoInicial = calendar.get(Calendar.MINUTE)
                            _hrInicial.value = LocalTime.of(horaInicial, minutoInicial)

                            calendar.timeInMillis = endAtLong
                            val horaFinal = calendar.get(Calendar.HOUR_OF_DAY)
                            val minutoFinal = calendar.get(Calendar.MINUTE)
                            _hrFinal.value = LocalTime.of(horaFinal, minutoFinal)

                        }
                    }
                }
            }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    fun atualizaHomeTeam(name: String) {
        _homeTeamName.update { name }
    }

    fun atualizaAwayTeam(name: String) {
        _awayTeamName.update { name }
    }

    fun atualizaDtInicial(date: Long) {
        _dtInicial.update { date }
    }

    fun atualizaDtFinal(date: Long) {
        _dtFinal.update { date }
    }

    fun atualizaHrInicial(time: LocalTime) {
        _hrInicial.update { time }
    }

    fun atualizaHrFinal(time: LocalTime) {
        _hrFinal.update { time }
    }

    fun openDtInicialPicker() {
        _showDtInicialPicker.update { true }
    }

    fun openDtFinalPicker() {
        _showDtFinalPicker.update { true }
    }

    fun hideDtInicialPicker() {
        _showDtInicialPicker.update { false }
    }

    fun hideDtFinalPicker() {
        _showDtFinalPicker.update { false }
    }

    fun openHrInicialPicker() {
        _showHrInicialPicker.update { true }
    }

    fun openHrFinalPicker() {
        _showHrFinalPicker.update { true }
    }

    fun hideHrInicialPicker() {
        _showHrInicialPicker.update { false }
    }

    fun hideHrFinalPicker() {
        _showHrFinalPicker.update { false }
    }

    fun onSaveMatchClicked(
        onSuccess: () -> Unit,
        onFail: (error: String) -> Unit,
    ) {

        if (_isSaving.value) {
            return
        }

        viewModelScope.launch {
            salvarPartida(
                onSuccess = onSuccess,
                onFail = onFail
            )
        }
    }

    suspend fun salvarPartida(
        onSuccess: () -> Unit,
        onFail: (error: String) -> Unit,
    ) {
        try {
            _isSaving.update { true }

            if (_homeTeamName.value.isEmpty()) {
                onFail("O time da casa precisa ser informado")
                return
            }

            if (_awayTeamName.value.isEmpty()) {
                onFail("O time da visitante precisa ser informado")
                return
            }

            if (_dtInicial.value == null) {
                onFail("A data inicial do jogo não pode ficar em branco")
                return
            }

            if (_hrInicial.value == null) {
                onFail("A hora inicial do jogo não pode ficar em branco")
                return
            }

            if (_dtFinal.value == null) {
                onFail("A data final do jogo não pode ficar em branco")
                return
            }

            if (_hrFinal.value == null) {
                onFail("A hora final do jogo não pode ficar em branco")
                return
            }

            throw Exception("Comparar data inicial com final")

            val newMatch = Match(
                id = _match.value.id,
                homeTeam = _homeTeamName.value,
                homeScore = 0,
                awayTeam = _awayTeamName.value,
                awayScore = 0,
                createAt = Date(),
                startAt = Date(),
                endAt = Date(),
            )

            if (newMatch.id > 0) {
                matchRepository.updateMatch(newMatch)
            } else {
                matchRepository.insertMatch(newMatch)
            }

            onSuccess()

        } catch (e: Exception) {
            onFail("Ops, um problema aconteceu durante a criação da partida.\n${e.message}")

        } finally {
            _isSaving.update { false }
        }
    }


    private fun formatarDataEHora(dataMillis: Long?, hora: LocalTime?): String {

        val dataFormatada: String? = dataMillis?.let {
            val data: Date = Date(dataMillis)
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            formato.timeZone = TimeZone.getTimeZone("UTC")
            formato.format(data)
        }

        val horaFormatada: String? = hora?.let {
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
            it.format(timeFormatter)
        }

        return when {
            dataFormatada != null && horaFormatada != null -> "$dataFormatada $horaFormatada"
            dataFormatada != null -> dataFormatada
            horaFormatada != null -> horaFormatada
            else -> "Selecionar data"
        }
    }

}