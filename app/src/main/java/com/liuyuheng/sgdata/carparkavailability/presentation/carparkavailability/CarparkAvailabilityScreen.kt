package com.liuyuheng.sgdata.carparkavailability.presentation.carparkavailability

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.carparkavailability.presentation.CarparkAvailabilityViewModel
import com.liuyuheng.sgdata.core.presentation.components.BasePreviewComposable

@Composable
fun CarparkAvailabilityScreen(
    viewModel: CarparkAvailabilityViewModel,
    onNavigateToCarparkInfo: () -> Unit
) {

    CarparkAvailabilityScreen(
        onNavigateToCarparkInfo = onNavigateToCarparkInfo
    )
}

@Composable
private fun CarparkAvailabilityScreen(
    onNavigateToCarparkInfo: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = { onNavigateToCarparkInfo() }
        ) {
            Text("Go to Carpark Info")
        }
    }
}

@Preview
@Composable
private fun CarparkAvailabilityScreenPreview() {
    BasePreviewComposable {
        CarparkAvailabilityScreen(
            onNavigateToCarparkInfo = {},
        )
    }
}