package com.br.leonardo.bays.crud.ui.match.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.ui.home.widgets.HomeEmptyList
import com.br.leonardo.bays.crud.ui.home.widgets.HomeFail
import com.br.leonardo.bays.crud.ui.home.widgets.HomeList
import com.br.leonardo.bays.crud.ui.home.widgets.HomeLoading
import com.br.leonardo.bays.crud.viewmodel.home.HomeState
import com.br.leonardo.bays.crud.viewmodel.home.HomeViewModel
import com.br.leonardo.bays.crud.viewmodel.match.MatchState
import com.br.leonardo.bays.crud.viewmodel.match.MatchViewModel

@Composable
fun MatchContent(viewModel: MatchViewModel) {

    val state by viewModel.state.collectAsState()

    when (state) {
        is MatchState.Loading -> MatchLoading()
        is MatchState.Success -> MatchScore(
            viewModel,
            (state as MatchState.Success).match,
        )

        is MatchState.Error -> HomeFail((state as MatchState.Error).message)
    }
}