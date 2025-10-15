package com.br.leonardo.bays.crud.ui.manager_match.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.Instant
import java.time.temporal.ChronoUnit


//@Composable
//fun DatePickerFieldToModal() {
//    var selectedDate by remember { mutableStateOf<Long?>(null) }
//    var showModal by remember { mutableStateOf(false) }
//
//    OutlinedTextField(
//        value = selectedDate?.let { convertMillisToDate(it) } ?: "",
//        onValueChange = { },
//        label = { Text("DOB") },
//        placeholder = { Text("MM/DD/YYYY") },
//        trailingIcon = {
//            Icon(Icons.Default.DateRange, contentDescription = "Select date")
//        },
//        modifier = modifier
//            .fillMaxWidth()
//            .pointerInput(selectedDate) {
//                awaitEachGesture {
//                    // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
//                    // in the Initial pass to observe events before the text field consumes them
//                    // in the Main pass.
//                    awaitFirstDown(pass = PointerEventPass.Initial)
//                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
//                    if (upEvent != null) {
//                        showModal = true
//                    }
//                }
//            }
//    )
//
//    if (showModal) {
//        DatePickerModal(
//            onDateSelected = { selectedDate = it },
//            onDismiss = { showModal = false }
//        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
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
