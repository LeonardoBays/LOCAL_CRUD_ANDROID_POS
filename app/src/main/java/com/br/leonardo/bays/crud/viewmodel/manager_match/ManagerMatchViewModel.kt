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

    fun loadMatch(matchId: Long?) {
        try {
            matchId?.let {
                viewModelScope.launch {
                    matchRepository.getMatchById(matchId).collect { match ->
                        match?.let {
                            _match.value = match
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

    fun salvarPartida() {}
}