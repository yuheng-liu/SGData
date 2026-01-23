package com.liuyuheng.sgdata.presentation.shared.timeslotdropdown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSlotDropdown(
    onTimeSlotSelected: (LocalTime) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedTime,
            onValueChange = {},
            readOnly = true,
            label = { Text("Time Slot") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            timeSlots.forEach { timeSlot ->
                DropdownMenuItem(
                    text = { Text(timeSlot.toString()) },
                    onClick = {
                        selectedTime = timeSlot.toString()
                        onTimeSlotSelected(timeSlot)
                        expanded = false
                    }
                )
            }
        }
    }
}

private val timeSlots: List<LocalTime> = (0..22 step 2)
    .map { hour ->
        LocalTime.of(hour, 0, 0)
    }

@Preview
@Composable
private fun TimeSlotDropdownPreview() {
    BasePreviewComposable {
        TimeSlotDropdown { }
    }
}