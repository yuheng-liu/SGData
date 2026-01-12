package com.liuyuheng.sgdata.presentation.shared.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HttpErrorDialog(
    errorMessage: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Error") },
        text = { Text(errorMessage) },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = "OK")
            }
        }
    )
}