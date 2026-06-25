package com.liuyuheng.sgdata.home.presentation

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `screensList emits ScreenType entries when collected`() = runTest {
        viewModel.screensList.test {
            // Under a StandardTestDispatcher, the test block is run.
            // When we start collecting using Turbine, onStart is executed.
            // Since it's StateFlow, it will immediately emit the initial value (emptyList),
            // and then because of onStart, _screensList.value is updated to ScreenType.entries,
            // which causes screensList to emit ScreenType.entries.

            // Consume the initial value (emptyList)
            assertEquals(emptyList<ScreenType>(), awaitItem())

            // Advance the dispatcher to allow the onStart coroutine block to execute
            advanceUntilIdle()

            // Consume the updated value containing ScreenType entries
            assertEquals(ScreenType.entries, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `screensList value is updated to ScreenType entries after initialization`() = runTest {
        viewModel.screensList.test {
            advanceUntilIdle()
            assertEquals(ScreenType.entries, viewModel.screensList.value)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
