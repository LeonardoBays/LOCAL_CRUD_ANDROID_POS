package com.br.leonardo.bays.crud.viewmodel.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.leonardo.bays.crud.data.repository.match.MatchRepository
import com.br.leonardo.bays.crud.domain.model.Match
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MatchState>(MatchState.Loading)
    val state: StateFlow<MatchState> = _state

    fun loadMatch(matchId: Long) {
        try {
            _state.value = MatchState.Loading

            viewModelScope.launch {
                matchRepository.getMatchById(matchId).collect { match ->

                    match?.let {
                        _state.value = MatchState.Success(it)
                    } ?: run {
                        _state.value = MatchState.Error("Partida não encontrada")
                    }
                }
            }

        } catch (e: Exception) {
            _state.value = MatchState.Error("Falha ao encontrar partida.\n${e.message}")
        }
    }

    fun decreaseHome() {
        if (state.value is MatchState.Success) {

            val match: Match = (state.value as MatchState.Success).match

            val newMatch = match.decreaseHome();

            viewModelScope.launch {
                try {
                    matchRepository.updateHomeScore(newMatch.id, newMatch.homeScore)
                    _state.value = MatchState.Success(newMatch)
                } catch (e: Exception) {
                    _state.value =
                        MatchState.Error("Não foi possível atualizar o placar do jogo.\n${e.message}")
                }
            }

        }

    }

    fun decreaseAway() {
        if (state.value is MatchState.Success) {

            val match: Match = (state.value as MatchState.Success).match

            val newMatch = match.decreaseAway();

            viewModelScope.launch {
                try {
                    matchRepository.updateAwayScore(newMatch.id, newMatch.homeScore)
                    _state.value = MatchState.Success(newMatch)
                } catch (e: Exception) {
                    _state.value =
                        MatchState.Error("Não foi possível atualizar o placar do jogo.\n${e.message}")
                }
            }

        }

    }

    fun increaseAway() {
        if (state.value is MatchState.Success) {

            val match: Match = (state.value as MatchState.Success).match

            val newMatch = match.increaseAway();

            viewModelScope.launch {
                try {
                    matchRepository.updateAwayScore(newMatch.id, newMatch.homeScore)
                    _state.value = MatchState.Success(newMatch)
                } catch (e: Exception) {
                    _state.value =
                        MatchState.Error("Não foi possível atualizar o placar do jogo.\n${e.message}")
                }
            }

        }

    }

    fun increaseHome() {
        if (state.value is MatchState.Success) {

            val match: Match = (state.value as MatchState.Success).match

            val newMatch = match.increaseHome();

            viewModelScope.launch {
                try {
                    matchRepository.updateHomeScore(newMatch.id, newMatch.homeScore)
                    _state.value = MatchState.Success(newMatch)
                } catch (e: Exception) {
                    _state.value =
                        MatchState.Error("Não foi possível atualizar o placar do jogo.\n${e.message}")
                }
            }

        }

    }

}