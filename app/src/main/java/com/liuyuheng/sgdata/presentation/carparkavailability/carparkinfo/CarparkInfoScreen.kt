package com.liuyuheng.sgdata.presentation.carparkavailability.carparkinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo
import com.liuyuheng.sgdata.domain.model.carpark.CarparkType
import com.liuyuheng.sgdata.domain.model.carpark.ParkingSystemType
import com.liuyuheng.sgdata.presentation.carparkavailability.CarparkAvailabilityViewModel
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.searchbar.Searchbar
import com.liuyuheng.sgdata.shared.toYesNo

@Composable
fun CarparkInfoScreen(
    viewModel: CarparkAvailabilityViewModel,
) {
    val carparkInfoList by viewModel.filteredCarparkInfoList.collectAsStateWithLifecycle()

    CarparkInfoScreen(
        carparkInfoList = carparkInfoList,
        queryString = viewModel.queryString.collectAsStateWithLifecycle().value,
        onQueryStringChanged = viewModel::onQueryStringChanged
    )
}

@Composable
private fun CarparkInfoScreen(
    carparkInfoList: List<CarparkInfo>,
    queryString: String,
    onQueryStringChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Searchbar(queryString) { onQueryStringChanged(it) }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = Dimensions.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
        ) {
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
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
            carparkInfoList = listOf(
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
            onQueryStringChanged = {}
        )
    }
}