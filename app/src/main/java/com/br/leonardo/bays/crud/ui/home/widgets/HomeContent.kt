package com.br.leonardo.bays.crud.ui.home.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.br.leonardo.bays.crud.viewmodel.home.HomeState
import com.br.leonardo.bays.crud.viewmodel.home.HomeViewModel

@Composable
fun HomeContent(viewModel: HomeViewModel) {

    val state by viewModel.uiState.collectAsState()

    when(state) {
        is HomeState.Loading -> HomeLoading()
        is HomeState.Empty -> HomeEmptyList(viewModel)
        is HomeState.Success -> HomeList((state as HomeState.Success).todos)
        is HomeState.Error -> HomeFail((state as HomeState.Error).message)
    }
}