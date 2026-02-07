package com.liuyuheng.sgdata.presentation.carparkavailability.carparkinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo
import com.liuyuheng.sgdata.domain.model.carpark.CarparkType
import com.liuyuheng.sgdata.domain.model.carpark.ParkingSystemType
import com.liuyuheng.sgdata.presentation.carparkavailability.CarparkAvailabilityViewModel
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.SGDataSpacer
import com.liuyuheng.sgdata.presentation.shared.searchbar.Searchbar
import com.liuyuheng.sgdata.shared.toYesNo

@Composable
fun CarparkInfoScreen(
    viewModel: CarparkAvailabilityViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CarparkInfoScreen(
        uiState = uiState,
        onQueryStringChanged = viewModel::onQueryStringChanged,
        onUpdateCarparkInfoDataset = viewModel::updateCarparkInfoDataset,
    )
}

@Composable
private fun CarparkInfoScreen(
    uiState: CarparkInfoUiState,
    onQueryStringChanged: (String) -> Unit,
    onUpdateCarparkInfoDataset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimensions.paddingMedium)
    ) {
        if (uiState.lastUpdated.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Last retrieved at: ${uiState.lastUpdated}",
                )
                IconButton(onClick = onUpdateCarparkInfoDataset) {
                    Icon(
                        imageVector = Icons.Filled.Refresh, contentDescription = null
                    )
                }
            }
        }
        Searchbar(
            query = uiState.queryString,
            hintText = "Search by Name or Address",
            onQueryChanged = onQueryStringChanged
        )
        SGDataSpacer()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
        ) {
            val carparkInfoList = uiState.filteredCarparkInfoList
            items(
                count = carparkInfoList.size,
                key = { carparkInfoList[it].carparkId }
            ) { index ->
                CarparkInfoItem(carparkInfoList[index])
            }
        }
    }
}

@Composable
private fun CarparkInfoItem(
    carparkInfo: CarparkInfo
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(Dimensions.paddingMedium)
                .fillMaxWidth()
        ) {
            Text(
                text = carparkInfo.carparkId,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Address: ${carparkInfo.address}",
            )
            Text("Type: ${carparkInfo.carparkType.displayText}")
            Text("Parking System: ${carparkInfo.parkingSystemType.displayText}")
            Text("Decks: ${carparkInfo.carparkDecks} Floors")
            Text("Gantry Height: ${carparkInfo.gantryHeight} metres")
            Text("Short Term Parking: ${carparkInfo.shortTermParkingTiming}")
            Text("Free Parking: ${carparkInfo.freeParkingTiming}")
            Text("Night Parking: ${carparkInfo.nightParkingAvailable.toYesNo()}")
            Text("Basement: ${carparkInfo.carparkBasementAvailable.toYesNo()}")
        }
    }
}

@Preview
@Composable
private fun CarparkInfoScreenPreview() {
    BasePreviewComposable {
        CarparkInfoScreen(
            uiState = CarparkInfoUiState(
                filteredCarparkInfoList = listOf(
                    CarparkInfo(
                        carparkId = "CarparkId1",
                        address = "Address",
                        coordinates = CarparkInfo.Coordinates(1.0, 2.0),
                        carparkType = CarparkType.MULTI_STOREY,
                        parkingSystemType = ParkingSystemType.ELECTRONIC,
                        shortTermParkingTiming = "WHOLE DAY",
                        freeParkingTiming = "SUN & PH FR 7AM-10.30PM",
                        nightParkingAvailable = true,
                        carparkDecks = 10,
                        gantryHeight = 2.0,
                        carparkBasementAvailable = false
                    ),
                    CarparkInfo(
                        carparkId = "CarparkId2",
                        address = "Address",
                        coordinates = CarparkInfo.Coordinates(1.0, 2.0),
                        carparkType = CarparkType.MULTI_STOREY,
                        parkingSystemType = ParkingSystemType.ELECTRONIC,
                        shortTermParkingTiming = "7AM-7PM",
                        freeParkingTiming = "NO",
                        nightParkingAvailable = true,
                        carparkDecks = 10,
                        gantryHeight = 2.0,
                        carparkBasementAvailable = false
                    )
                ),
                queryString = "",
                lastUpdated = "12th Jan"
            ),
            onQueryStringChanged = {},
            onUpdateCarparkInfoDataset = {}
        )
    }
}