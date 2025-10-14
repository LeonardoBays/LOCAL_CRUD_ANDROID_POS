package com.br.leonardo.bays.crud.ui.match.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchTopBar(navController: NavController) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Placar do Jogo")
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                androidx.compose.material3.Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    null
                )
            }
        }
    )
}