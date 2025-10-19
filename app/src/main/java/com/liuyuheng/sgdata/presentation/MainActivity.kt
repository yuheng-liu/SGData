package com.liuyuheng.sgdata.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.liuyuheng.sgdata.domain.entity.Bus
import com.liuyuheng.sgdata.presentation.theme.SGDataTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: BusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadBuses()
        setContent {
            SGDataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val buses by viewModel.buses.collectAsState()
                    BusList(
                        buses = buses,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusList(buses: List<Bus>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(buses) { bus ->
            Text(text = "Bus ${bus.number}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusListPreview() {
    SGDataTheme {
        BusList(buses = listOf(Bus("1", "10"), Bus("2", "20")))
    }
}