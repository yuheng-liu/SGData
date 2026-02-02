package com.liuyuheng.sgdata.presentation.carparkavailability

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable

@Composable
fun CarparkAvailabilityScreen(
    viewModel: CarparkAvailabilityViewModel
) {

    CarparkAvailabilityScreen(
        onClickButton = viewModel::getCarparkInfoDataset
    )
}

@Composable
private fun CarparkAvailabilityScreen(
    onClickButton: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = { onClickButton() }
        ) {
            Text("Get Carpark Data")
        }
    }
}

@Preview
@Composable
private fun CarparkAvailabilityScreenPreview() {
    BasePreviewComposable {
        CarparkAvailabilityScreen(
            onClickButton = {},
        )
    }
}