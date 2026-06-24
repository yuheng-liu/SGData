package com.liuyuheng.sgdata.core.presentation.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SimpleErrorDialog(
    errorMessage: String,
    onDismiss: (() -> Unit)? = null,
) {
    AlertDialog(
        onDismissRequest = onDismiss ?: {},
        title = { Text(text = "Error") },
        text = { Text(errorMessage) },
        confirmButton = {
            Button(onClick = { onDismiss?.invoke() }) {
                Text(text = "OK")
            }
        }
    )
}