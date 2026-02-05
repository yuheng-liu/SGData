package com.liuyuheng.sgdata.presentation.carparkavailability.carparkinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.domain.model.carpark.CarparkInfo
import com.liuyuheng.sgdata.domain.model.carpark.CarparkType
import com.liuyuheng.sgdata.domain.model.carpark.ParkingSystemType
import com.liuyuheng.sgdata.presentation.carparkavailability.CarparkAvailabilityViewModel
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.shared.searchbar.Searchbar

@Composable
fun CarparkInfoScreen(
    viewModel: CarparkAvailabilityViewModel,
) {
    val carparkInfoList by viewModel.carparkInfoList.collectAsStateWithLifecycle()

    CarparkInfoScreen(
        carparkInfoList = carparkInfoList,
        onQueryStringChanged = viewModel::onQueryStringChanged
    )
}

@Composable
private fun CarparkInfoScreen(
    carparkInfoList: List<CarparkInfo>,
    onQueryStringChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Searchbar { onQueryStringChanged(it) }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = Dimensions.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)
        ) {
            items(
                count = carparkInfoList.size,
                key = { carparkInfoList[it].carparkId }
            ) { index ->
                val carparkInfo = carparkInfoList[index]
                Text(carparkInfo.carparkId)
            }
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
                    shortTermParkingTiming = "shortTermParkingTiming",
                    freeParkingTiming = "freeParkingTiming",
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
                    shortTermParkingTiming = "shortTermParkingTiming",
                    freeParkingTiming = "freeParkingTiming",
                    nightParkingAvailable = true,
                    carparkDecks = 10,
                    gantryHeight = 2.0,
                    carparkBasementAvailable = false
                )
            ),
            onQueryStringChanged = {}
        )
    }
}