package com.br.leonardo.bays.crud.ui.home.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.viewmodel.home.HomeState
import com.br.leonardo.bays.crud.viewmodel.home.HomeViewModel

@Composable
fun HomeContent(navController: NavController, viewModel: HomeViewModel) {

    val state by viewModel.uiState.collectAsState()

    when (state) {
        is HomeState.Loading -> HomeLoading()
        is HomeState.Empty -> HomeEmptyList(navController, viewModel)
        is HomeState.Success -> HomeList(
            navController,
            (state as HomeState.Success).todos,
        )

        is HomeState.Error -> HomeFail((state as HomeState.Error).message)
    }
}