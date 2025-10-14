package com.br.leonardo.bays.crud.ui.manager_match.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.viewmodel.manager_match.ManagerMatchViewModel

@Composable
fun ManagerMatchContent(navController: NavController, viewModel: ManagerMatchViewModel) {

    val match by viewModel.match.collectAsState()

    Column(Modifier.padding(16.dp)) {

        ManagerMatchInput(
            label = "Time da casa",
            value = match.homeTeam,
            onValueChange = viewModel::atualizaHomeTeam
        )

        Spacer(modifier = Modifier.height(16.dp))

        ManagerMatchInput(
            label = "Time visitante",
            value = match.awayTeam,
            onValueChange = viewModel::atualizaAwayTeam
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = viewModel::salvarPartida) {

            val text = if(match.isCreating()) "Cadastrar partida" else "Atualizar partida"

            Text(text)
        }
    }

}