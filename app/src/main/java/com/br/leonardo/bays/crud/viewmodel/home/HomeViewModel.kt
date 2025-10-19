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
}