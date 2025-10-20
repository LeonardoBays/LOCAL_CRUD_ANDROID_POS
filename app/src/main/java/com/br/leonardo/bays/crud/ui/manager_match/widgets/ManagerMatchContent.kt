package com.br.leonardo.bays.crud.ui.manager_match.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.leonardo.bays.crud.viewmodel.manager_match.ManagerMatchViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset

@Composable
fun ManagerMatchContent(navController: NavController, viewModel: ManagerMatchViewModel) {

    val error by viewModel.error.collectAsState()

    if (error != null) {
        ManagerMatchFail(error!!)
        return
    }

    val showDtInicialPicker by viewModel.showDtInicialPicker.collectAsState()
    val showDtFinalPicker by viewModel.showDtFinalPicker.collectAsState()
    val showHrInicialPicker by viewModel.showHrInicialPicker.collectAsState()
    val showHrFinalPicker by viewModel.showHrFinalPicker.collectAsState()
    val dtInicial by viewModel.dtInicial.collectAsState()
    val dtFinal by viewModel.dtFinal.collectAsState()
    val hrInicial by viewModel.hrInicial.collectAsState()
    val hrFinal by viewModel.hrFinal.collectAsState()
    val showErrorDialog by viewModel.showErrorDialog.collectAsState()
    val showDeleteDialog by viewModel.showDeleteDialog.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isCreating by viewModel.isCreating.collectAsState()
    val dateLabelInicial = viewModel.dateLabelInicial
    val dateLabelFinal = viewModel.dateLabelFinal
    val isSaving = viewModel.isSaving.collectAsState()
    val homeTeamName = viewModel.homeTeamName.collectAsState()
    val awayTeamName = viewModel.awayTeamName.collectAsState()

    Column(
        Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            ManagerMatchInput(
                label = "Time da casa",
                value = homeTeamName.value,
                onValueChange = viewModel::atualizaHomeTeam
            )

            Spacer(modifier = Modifier.height(8.dp))

            ManagerMatchInput(
                label = "Time visitante",
                value = awayTeamName.value,
                onValueChange = viewModel::atualizaAwayTeam
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ManagerMatchCustomButton(
                    onClick = viewModel::openDtInicialPicker,
                    text = dateLabelInicial,
                    modifier = Modifier.weight(1f),
                )

                Spacer(modifier = Modifier.width(8.dp))

                ManagerMatchCustomButton(
                    onClick = viewModel::openDtFinalPicker,
                    text = dateLabelFinal,
                    modifier = Modifier.weight(1f),
                )
            }

            if (showDtInicialPicker) {
                val minDate =
                    LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()

                ManagerMatchDatePicker(
                    onDateSelected = {
                        it?.let {
                            viewModel.atualizaDtInicial(it)
                            viewModel.openHrInicialPicker()
                        }
                    },
                    onDismiss = { viewModel.hideDtInicialPicker() },
                    maxDateMillis = dtFinal,
                    minDateMillis = minDate,
                    initialDateMillis = dtInicial,
                )
            }

            if (showDtFinalPicker) {
                val minDate =
                    LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()

                ManagerMatchDatePicker(
                    onDateSelected = {
                        it?.let {
                            viewModel.atualizaDtFinal(it)
                            viewModel.openHrFinalPicker()
                        }
                    },
                    onDismiss = { viewModel.hideDtFinalPicker() },
                    maxDateMillis = null,
                    minDateMillis = dtInicial ?: minDate,
                    initialDateMillis = dtFinal,
                )
            }

            if (showHrInicialPicker) {

                val time = hrInicial ?: LocalTime.now()

                ManagerMatchHourPicker(
                    onDismiss = viewModel::hideHrInicialPicker,
                    onTimeSelected = { hour, minute ->
                        viewModel.atualizaHrInicial(
                            LocalTime.of(
                                hour, minute
                            )
                        )
                    },
                    hour = time.hour,
                    minute = time.minute,
                )
            }

            if (showHrFinalPicker) {

                val time = hrFinal ?: LocalTime.now()

                ManagerMatchHourPicker(
                    onDismiss = viewModel::hideHrFinalPicker,
                    onTimeSelected = { hour, minute ->
                        viewModel.atualizaHrFinal(
                            LocalTime.of(
                                hour, minute
                            )
                        )
                    },
                    hour = time.hour,
                    minute = time.minute,
                )
            }

            if (showErrorDialog) {
                AlertDialog(
                    onDismissRequest = viewModel::hideErrorDialog,
                    confirmButton = {
                        TextButton(onClick = viewModel::hideErrorDialog) {
                            Text(text = "Ok")
                        }
                    },
                    text = { Text(text = errorMessage ?: "Ops, algo inesperado aconteceu!") }
                )
            }

            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = viewModel::hideDeleteDialog,
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.hideDeleteDialog()
                                viewModel.deleteMatch(
                                    onSuccess = { navController.popBackStack() },
                                    onFail = viewModel::openErrorDialog,
                                )
                            }) {
                            Text(text = "Sim")
                        }
                    },
                    dismissButton = {
                        Button(onClick = viewModel::hideDeleteDialog) {
                            Text(text = "Não")
                        }
                    },
                    text = { Text(text = "Tem certeza que deseja remover a partida?") }
                )
            }

        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.1f),
            onClick = {
                if (!isSaving.value) {
                    viewModel.onSaveMatchClicked(
                        onSuccess = { navController.popBackStack() },
                        onFail = viewModel::openErrorDialog,
                    )
                }
            }
        ) {

            if (isSaving.value) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                val text = if (isCreating) "Cadastrar partida" else "Atualizar partida"
                Text(text)
            }
        }
    }

}