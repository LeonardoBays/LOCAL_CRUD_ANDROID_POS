package com.br.leonardo.bays.crud.viewmodel.home

import com.br.leonardo.bays.crud.domain.model.Match

sealed class HomeState {
    object Loading : HomeState()
    object Empty : HomeState()
    data class Success(val todos: List<Match>) : HomeState()
    data class Error(val message: String) : HomeState()
}
