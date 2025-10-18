package com.br.leonardo.bays.crud.ui.manager_match.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerMatchHourPicker(
    onDismiss: () -> Unit,
    onTimeSelected: (hour: Int, minute: Int) -> Unit,
    hour: Int,
    minute: Int,
) {
    val timeState = rememberTimePickerState(
        initialHour = hour,
        initialMinute = minute,
    )

    AlertDialog(
        onDismissRequest = onDismiss,

        text = {
            TimePicker(state = timeState)
        },

        confirmButton = {
            Button(
                onClick = {
                    onTimeSelected(timeState.hour, timeState.minute)
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },

        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}