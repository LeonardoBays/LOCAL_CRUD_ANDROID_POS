package com.br.leonardo.bays.crud.ui.manager_match.widgets

import android.widget.Space
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.viewmodel.manager_match.ManagerMatchViewModel
import java.time.LocalDate
import java.time.ZoneOffset

@Composable
fun ManagerMatchContent(navController: NavController, viewModel: ManagerMatchViewModel) {

    val error by viewModel.error.collectAsState()

    if (error != null) {
        ManagerMatchFail(error!!)
        return
    }


    val match by viewModel.match.collectAsState()
    val showDtInicialPicker by viewModel.showDtInicialPicker.collectAsState()
    val showDtFinalPicker by viewModel.showDtFinalPicker.collectAsState()
    val dtInicial by viewModel.dtInicial.collectAsState()
    val dtFinal by viewModel.dtFinal.collectAsState()
    val scrollState = rememberScrollState()

    Column(Modifier.horizontalScroll(scrollState)) {

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

            val text = if (match.isCreating()) "Cadastrar partida" else "Atualizar partida"

            Text(text)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button( modifier = Modifier.fillMaxWidth(),onClick = { viewModel.openDtInicialPicker() }) {
                Text("Data inicial")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = { viewModel.openDtFinalPicker() }) {
                Text("Data final")
            }
        }

        if (showDtInicialPicker) {
            val minDate = LocalDate.now()
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli()

            DatePickerModal(
                onDateSelected = {
                    it?.let {
                        viewModel.atualizaDtInicial(it)
                    }
                },
                onDismiss = { viewModel.hideDtInicialPicker() },
                maxDateMillis = dtFinal,
                minDateMillis = minDate,
                initialDateMillis = dtInicial,
            )
        }

        if (showDtFinalPicker) {
            val minDate = LocalDate.now()
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli()

            DatePickerModal(
                onDateSelected = {
                    it?.let {
                        viewModel.atualizaDtFinal(it)
                    }
                },
                onDismiss = { viewModel.hideDtFinalPicker() },
                maxDateMillis = null,
                minDateMillis = dtInicial ?: minDate,
                initialDateMillis = dtFinal,
            )
        }

    }

}