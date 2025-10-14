package com.br.leonardo.bays.crud.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.leonardo.bays.crud.data.db.entity.MatchEntity
import com.br.leonardo.bays.crud.data.repository.match.MatchRepository
import com.br.leonardo.bays.crud.domain.model.Match
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeState>(HomeState.Loading)
    val uiState: StateFlow<HomeState> = _uiState

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {

            _uiState.value = HomeState.Loading
            try {
                matchRepository.getAllMatch().collectLatest { list ->
                    when (list.isEmpty()) {
                        true -> _uiState.value = HomeState.Empty
                        false -> _uiState.value = HomeState.Success(list)
                    }

                }
            } catch (e: Exception) {
                _uiState.value = HomeState.Error("Falha ao carregar a lista: ${e.message}")
            }

        }
    }

    private fun deleteMatch(match: Match) {
        viewModelScope.launch {
            matchRepository.deleteMatch(match)
        }
    }

    fun saveMatch() {

        val calendar = Calendar.getInstance()
        calendar.time = Date()

        val startAt: Date = calendar.time

        calendar.add(Calendar.MINUTE, 80)
        val endAt: Date = calendar.time

        val match = Match(
            id = 0,
            homeTeam = "Agropesca Jacare",
            homeScore = 1,
            awayTeam = "Estagiarios Reservas",
            awayScore = 0,
            createAt = Date(),
            startAt = startAt,
            endAt = endAt,
        )

        viewModelScope.launch {
            matchRepository.insertMatch(match)
        }
    }

}