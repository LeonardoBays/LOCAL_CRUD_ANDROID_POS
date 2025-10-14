package com.br.leonardo.bays.crud.viewmodel.manager_match

import com.br.leonardo.bays.crud.domain.model.Match
import com.br.leonardo.bays.crud.viewmodel.match.MatchState

sealed class ManagerMatchState() {



    object Loading : ManagerMatchState()
    data class Success(val match: Match) : ManagerMatchState()
    data class Error(val message: String) : ManagerMatchState()
}