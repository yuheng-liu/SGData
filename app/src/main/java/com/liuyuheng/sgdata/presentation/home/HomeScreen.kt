package com.liuyuheng.sgdata.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.liuyuheng.sgdata.R
import com.liuyuheng.sgdata.presentation.main.BasePreviewComposable
import com.liuyuheng.sgdata.presentation.main.theme.Dimensions
import com.liuyuheng.sgdata.presentation.main.theme.OnSurfaceLight
import com.liuyuheng.sgdata.presentation.main.theme.SurfaceLight

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToWeatherForecast: () -> Unit,
) {
    val screensList by viewModel.screensList.collectAsStateWithLifecycle()

    HomeScreen(
        screensList = screensList,
        onNavigateToWeatherForecast = onNavigateToWeatherForecast,
    )
}

@Composable
fun HomeScreen(
    screensList: List<ScreenType>,
    onNavigateToWeatherForecast: () -> Unit,
) {
    Column {
        Text(
            text = "SG Data",
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = Dimensions.paddingLarge),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(Dimensions.paddingMedium),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium),
        ) {
            items(screensList.size) { index ->
                val screenType = screensList[index]

                ScreenCard(
                    screenType = screenType,
                    onClick = {
                        onNavigateToWeatherForecast.invoke()
                    }
                )
            }
        }
    }
}

@Composable
fun ScreenCard(
    screenType: ScreenType,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimensions.paddingMedium),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceLight,
            contentColor = OnSurfaceLight,
        ),
    ) {
        Icon(
            painter = painterResource(R.drawable.image_weather),
            contentDescription = null,
            modifier = Modifier
                .padding(all = Dimensions.paddingMedium),
            tint = Color.Unspecified,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = screenType.displayName,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    BasePreviewComposable {
        HomeScreen(
            screensList = listOf(ScreenType.WEATHER_FORECAST),
            onNavigateToWeatherForecast = {},
        )
    }
}