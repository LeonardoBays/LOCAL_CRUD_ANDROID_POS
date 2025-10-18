package com.br.leonardo.bays.crud.ui.manager_match.widgets

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerMatchDatePicker(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    minDateMillis: Long? = null,
    maxDateMillis: Long? = null,
    initialDateMillis: Long? = null,
) {

    val selectableDatesValidator = remember(minDateMillis) {
        object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                var isValid = true

                if (minDateMillis != null) {
                    isValid = isValid && (utcTimeMillis >= minDateMillis)
                }

                if (maxDateMillis != null) {
                    isValid = isValid && (utcTimeMillis <= maxDateMillis)
                }

                return isValid
            }
        }
    }

    val datePickerState = rememberDatePickerState(
        selectableDates = selectableDatesValidator,
        initialSelectedDateMillis = initialDateMillis,
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
