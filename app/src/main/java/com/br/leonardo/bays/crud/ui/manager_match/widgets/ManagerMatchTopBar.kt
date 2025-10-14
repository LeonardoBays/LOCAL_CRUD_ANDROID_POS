package com.br.leonardo.bays.crud.ui.manager_match.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.viewmodel.manager_match.ManagerMatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerMatchTopBar(navController: NavController, viewModel: ManagerMatchViewModel) {
    CenterAlignedTopAppBar(
        title = {

            val match by viewModel.match.collectAsState()

            val text = if (match.isCreating()) "Criar nova partida" else "Atualizar partida"

            Text(text = text)
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    null
                )
            }
        }
    )
}