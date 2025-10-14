package com.br.leonardo.bays.crud.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.ui.home.widgets.HomeContent
import com.br.leonardo.bays.crud.ui.home.widgets.HomeFAB
import com.br.leonardo.bays.crud.ui.theme.CRUDTheme
import com.br.leonardo.bays.crud.viewmodel.home.HomeState
import com.br.leonardo.bays.crud.viewmodel.home.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            val state by viewModel.uiState.collectAsState()
            if (state is HomeState.Success) HomeFAB(navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 24.dp)
        ) {
            HomeContent(navController = navController, viewModel = viewModel)
        }
    }
}
