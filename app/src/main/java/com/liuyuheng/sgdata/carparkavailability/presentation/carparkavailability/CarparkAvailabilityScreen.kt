package com.liuyuheng.sgdata.carparkavailability.presentation.carparkavailability

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo.Coordinates
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo.LotsAvailability
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkLotType
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkType
import com.liuyuheng.sgdata.carparkavailability.domain.models.ParkingSystemType
import com.liuyuheng.sgdata.core.presentation.components.BasePreviewComposable
import com.liuyuheng.sgdata.core.presentation.components.dialog.DialogTypes
import com.liuyuheng.sgdata.core.presentation.components.dialog.SimpleErrorDialog
import com.liuyuheng.sgdata.core.presentation.components.loader.Loader

@Composable
fun CarparkAvailabilityScreen(
    viewModel: CarparkAvailabilityViewModel = hiltViewModel(),
    onNavigateToCarparkDetails: () -> Unit
) {
    val uiState by viewModel.carparkParkAvailabilityUiState.collectAsStateWithLifecycle()
    val dialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    when (val currentDialog = dialogState) {
        is DialogTypes.SimpleError -> SimpleErrorDialog(
            errorMessage = currentDialog.message,
            onDismiss = { viewModel.onDismissErrorDialog() },
        )

        else -> Unit
    }

    when (val state = uiState) {
        is CarparkAvailabilityUiState.Idle,
        is CarparkAvailabilityUiState.Error -> {
        }

        is CarparkAvailabilityUiState.Loading -> {
            Loader()
        }

        is CarparkAvailabilityUiState.Loaded -> {
            CarparkAvailabilityScreen(
                uiState = state,
                onNavigateToCarparkDetails = onNavigateToCarparkDetails
            )
        }
    }
}

@Composable
private fun CarparkAvailabilityScreen(
    uiState: CarparkAvailabilityUiState.Loaded,
    onNavigateToCarparkDetails: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = { onNavigateToCarparkDetails() }
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
            uiState = CarparkAvailabilityUiState.Loaded(
                data = listOf(
                    CarparkInfo(
                        carparkId = "1",
                        address = "BLK 270/271 ALBERT CENTRE BASEMENT CAR PARK",
                        coordinates = Coordinates(30314.7936, 31490.4942),
                        carparkType = CarparkType.MULTI_STOREY,
                        parkingSystemType = ParkingSystemType.ELECTRONIC,
                        shortTermParkingTiming = "WHOLE DAY",
                        freeParkingTiming = "NO",
                        nightParkingAvailable = true,
                        carparkDecks = 3,
                        gantryHeight = 1.8,
                        carparkBasementAvailable = true,
                        lotsAvailability = listOf(
                            LotsAvailability(
                                totalLots = 105,
                                lotType = CarparkLotType.CARS,
                                availableLots = 101
                            )
                        )
                    )
                )
            ),
            onNavigateToCarparkDetails = {}
        )
    }
}