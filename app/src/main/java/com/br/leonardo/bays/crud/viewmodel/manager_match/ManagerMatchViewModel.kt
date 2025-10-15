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
import javax.inject.Inject

@HiltViewModel
class ManagerMatchViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _match = MutableStateFlow<Match>(Match())
    val match: StateFlow<Match> = _match

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _dtInicial = MutableStateFlow<Long?>(null)
    val dtInicial: StateFlow<Long?> = _dtInicial

    private val _dtFinal = MutableStateFlow<Long?>(null)
    val dtFinal: StateFlow<Long?> = _dtFinal


    private val _showDtInicialPicker = MutableStateFlow<Boolean>(false)
    val showDtInicialPicker: StateFlow<Boolean> = _showDtInicialPicker

    private val _showDtFinalPicker = MutableStateFlow<Boolean>(false)
    val showDtFinalPicker: StateFlow<Boolean> = _showDtFinalPicker

    fun loadMatch(matchId: Long?) {
        try {
            matchId?.let {
                viewModelScope.launch {
                    matchRepository.getMatchById(matchId).collect { match ->
                        match?.let {
                            _match.value = match

                            _dtInicial.value = match.startAt.time
                            _dtFinal.value = match.endAt.time

                        }
                    }
                }
            }
        } catch (e: Exception) {
            _error.value = e.message
        }
    }

    fun atualizaHomeTeam(name: String) {
        _match.update {
            it.copy(homeTeam = name)
        }
    }

    fun atualizaAwayTeam(name: String) {
        _match.update {
            it.copy(awayTeam = name)
        }
    }

    fun atualizaDtInicial(date: Long) { _dtInicial.update { date } }
    fun atualizaDtFinal(date: Long) { _dtFinal.update { date } }

    fun openDtInicialPicker() { _showDtInicialPicker.update { true } }
    fun openDtFinalPicker() { _showDtFinalPicker.update { true } }

    fun hideDtInicialPicker() { _showDtInicialPicker.update { false } }
    fun hideDtFinalPicker() { _showDtFinalPicker.update { false } }

    fun salvarPartida() {}
}