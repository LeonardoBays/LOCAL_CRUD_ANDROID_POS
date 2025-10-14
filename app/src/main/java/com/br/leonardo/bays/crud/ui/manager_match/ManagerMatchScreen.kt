package com.br.leonardo.bays.crud.ui.manager_match

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.ui.manager_match.widgets.ManagerMatchContent
import com.br.leonardo.bays.crud.ui.manager_match.widgets.ManagerMatchTopBar
import com.br.leonardo.bays.crud.viewmodel.manager_match.ManagerMatchViewModel

@Composable
fun ManagerMatchScreen(
    navController: NavController,
    viewModel: ManagerMatchViewModel,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ManagerMatchTopBar(navController, viewModel) },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 24.dp)
        ) {
            ManagerMatchContent(navController = navController, viewModel = viewModel)
        }
    }
}