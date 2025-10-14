package com.br.leonardo.bays.crud.viewmodel.match

import com.br.leonardo.bays.crud.domain.model.Match

sealed class MatchState {
    object Loading : MatchState()
    data class Success(val match: Match) : MatchState()
    data class Error(val message: String) : MatchState()
}
