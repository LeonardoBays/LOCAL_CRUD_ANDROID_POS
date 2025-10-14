package com.br.leonardo.bays.crud.ui.manager_match

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun ManagerMatchScreen(
    navController: NavController,
    matchId: Int?
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(
            text = "ManagerMatchScreen: $matchId",
            modifier = Modifier.padding(innerPadding)
        )
    }
}