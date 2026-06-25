package com.liuyuheng.sgdata.weatherforecast.presentation

import app.cash.turbine.Event.Item
import app.cash.turbine.test
import com.liuyuheng.sgdata.core.domain.models.DomainResult
import com.liuyuheng.sgdata.core.presentation.components.dialog.DialogTypes
import com.liuyuheng.sgdata.core.presentation.components.loader.LoadingStateHandler
import com.liuyuheng.sgdata.weatherforecast.domain.model.FourDayForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwentyFourHourForecast
import com.liuyuheng.sgdata.weatherforecast.domain.model.TwoHourForecast
import com.liuyuheng.sgdata.weatherforecast.domain.usecase.GetFourDayForecastUseCase
import com.liuyuheng.sgdata.weatherforecast.domain.usecase.GetTwentyFourHourForecastUseCase
import com.liuyuheng.sgdata.weatherforecast.domain.usecase.GetTwoHourForecastUseCase
import com.liuyuheng.sgdata.weatherforecast.presentation.twentyfourhour.TwentyFourHourForecastUiState
import com.liuyuheng.sgdata.weatherforecast.presentation.twohour.TwoHourForecastUiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherForecastViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    // Mocks
    private val loadingStateHandler: LoadingStateHandler = mockk(relaxed = true)
    private val getTwentyFourHourForecastUseCase: GetTwentyFourHourForecastUseCase = mockk()
    private val getTwoHourForecastUseCase: GetTwoHourForecastUseCase = mockk()
    private val getFourDayForecastUseCase: GetFourDayForecastUseCase = mockk()

    // SUT
    private lateinit var viewModel: WeatherForecastViewModel

    // Test data
    private val mockTwentyFourHourForecast = TwentyFourHourForecast()
    private val mockTwoHourForecast = TwoHourForecast()
    private val mockFourDayForecast = FourDayForecast()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        // Default success stubs so the init block does not crash
        coEvery { getTwentyFourHourForecastUseCase(any()) } returns
                DomainResult.Success(mockTwentyFourHourForecast)
        coEvery { getTwoHourForecastUseCase(any()) } returns
                DomainResult.Success(mockTwoHourForecast)
        coEvery { getFourDayForecastUseCase(any()) } returns
                DomainResult.Success(mockFourDayForecast)

        viewModel = WeatherForecastViewModel(
            loadingStateHandler = loadingStateHandler,
            getTwentyFourHourForecastUseCase = getTwentyFourHourForecastUseCase,
            getTwoHourForecastUseCase = getTwoHourForecastUseCase,
            getFourDayForecastUseCase = getFourDayForecastUseCase,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init - fetchTwentyFourHoursForecast is called automatically`() = runTest {
        advanceUntilIdle()
        coVerify(exactly = 1) { getTwentyFourHourForecastUseCase(any()) }
    }

    @Test
    fun `init - twentyFourHoursForecastUiState transitions through Loading and settles on Loaded`() = runTest {
        // runTest advances the dispatcher, so by the time the body runs the init
        // coroutine has already completed and the flow has settled on Loaded.
        // We verify the final state here; the Loading→Loaded sequence is covered
        // by the dedicated Loading test below.
        advanceUntilIdle()
        val state = viewModel.twentyFourHoursForecastUiState.value
        assertTrue(
            "Expected Loaded after init, but was $state",
            state is TwentyFourHourForecastUiState.Loaded
        )
    }

    @Test
    fun `fetchTwentyFourHoursForecast - success emits Loaded state`() = runTest {
        viewModel.twentyFourHoursForecastUiState.test {
            viewModel.fetchTwentyFourHoursForecast()
            advanceUntilIdle()

            val states = cancelAndConsumeRemainingEvents()
                .filterIsInstance<Item<TwentyFourHourForecastUiState>>()
                .map { it.value }

            assertTrue(
                "Expected at least one Loaded state",
                states.any { it is TwentyFourHourForecastUiState.Loaded }
            )
        }
    }

    @Test
    fun `fetchTwentyFourHoursForecast - NoData failure emits Error state and dialog`() = runTest {
        val errorMessage = "No data from API"
        coEvery { getTwentyFourHourForecastUseCase(any()) } returns
                DomainResult.Failure.NoData(errorMessage)

        viewModel.fetchTwentyFourHoursForecast()
        advanceUntilIdle()

        val forecastState = viewModel.twentyFourHoursForecastUiState.value
        assertTrue(
            "Expected Error state, got $forecastState",
            forecastState is TwentyFourHourForecastUiState.Error
        )
        assertEquals("No data available", (forecastState as TwentyFourHourForecastUiState.Error).errorMessage)

        val dialogState = viewModel.dialogState.value
        assertTrue(dialogState is DialogTypes.SimpleError)
        assertEquals(errorMessage, (dialogState as DialogTypes.SimpleError).message)
    }

    @Test
    fun `fetchTwentyFourHoursForecast - Unavailable failure emits Error state and dialog`() = runTest {
        val errorMessage = "Service unavailable"
        coEvery { getTwentyFourHourForecastUseCase(any()) } returns
                DomainResult.Failure.Unavailable(errorMessage)

        viewModel.fetchTwentyFourHoursForecast()
        advanceUntilIdle()

        val forecastState = viewModel.twentyFourHoursForecastUiState.value
        assertTrue(
            "Expected Error state, got $forecastState",
            forecastState is TwentyFourHourForecastUiState.Error
        )
        assertEquals("Unavailable", (forecastState as TwentyFourHourForecastUiState.Error).errorMessage)

        val dialogState = viewModel.dialogState.value
        assertTrue(dialogState is DialogTypes.SimpleError)
        assertEquals(errorMessage, (dialogState as DialogTypes.SimpleError).message)
    }

    @Test
    fun `fetchTwentyFourHoursForecast - sets Loading state before final result`() = runTest {
        // Let the init coroutine finish first so the flow settles to a known state.
        advanceUntilIdle()

        viewModel.twentyFourHoursForecastUiState.test {
            // Skip the current StateFlow value (Loaded from init) replayed on subscription.
            skipItems(1)

            viewModel.fetchTwentyFourHoursForecast()

            // After the coroutine body starts, Loading is emitted before the use-case call.
            val loadingItem = awaitItem()
            assertTrue(
                "Expected Loading, got $loadingItem",
                loadingItem is TwentyFourHourForecastUiState.Loading
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchTwoHoursForecast - success updates twoHoursForecastUiState with data`() = runTest {
        viewModel.fetchTwoHoursForecast()
        advanceUntilIdle()

        val state = viewModel.twoHoursForecastUiState.value
        assertEquals(mockTwoHourForecast, state.twoHourForecast)
    }

    @Test
    fun `fetchTwoHoursForecast - NoData failure shows error dialog`() = runTest {
        val errorMessage = "Two hour forecast not available"
        coEvery { getTwoHourForecastUseCase(any()) } returns
                DomainResult.Failure.NoData(errorMessage)

        viewModel.fetchTwoHoursForecast()
        advanceUntilIdle()

        val dialogState = viewModel.dialogState.value
        assertTrue(dialogState is DialogTypes.SimpleError)
        assertEquals(errorMessage, (dialogState as DialogTypes.SimpleError).message)
    }

    @Test
    fun `fetchTwoHoursForecast - Unavailable failure shows error dialog`() = runTest {
        val errorMessage = "Two hour forecast service down"
        coEvery { getTwoHourForecastUseCase(any()) } returns
                DomainResult.Failure.Unavailable(errorMessage)

        viewModel.fetchTwoHoursForecast()
        advanceUntilIdle()

        val dialogState = viewModel.dialogState.value
        assertTrue(dialogState is DialogTypes.SimpleError)
        assertEquals(errorMessage, (dialogState as DialogTypes.SimpleError).message)
    }

    @Test
    fun `fetchTwoHoursForecast - does not alter twoHoursForecastUiState on failure`() = runTest {
        val initialState = viewModel.twoHoursForecastUiState.value
        coEvery { getTwoHourForecastUseCase(any()) } returns
                DomainResult.Failure.NoData("error")

        viewModel.fetchTwoHoursForecast()
        advanceUntilIdle()

        assertEquals(initialState, viewModel.twoHoursForecastUiState.value)
    }

    @Test
    fun `fetchFourDaysForecast - success updates fourDaysForecastUiState with data`() = runTest {
        viewModel.fetchFourDaysForecast()
        advanceUntilIdle()

        val state = viewModel.fourDaysForecastUiState.value
        assertEquals(mockFourDayForecast, state.fourDayForecast)
    }

    @Test
    fun `fetchFourDaysForecast - NoData failure shows error dialog`() = runTest {
        val errorMessage = "Four day forecast not available"
        coEvery { getFourDayForecastUseCase(any()) } returns
                DomainResult.Failure.NoData(errorMessage)

        viewModel.fetchFourDaysForecast()
        advanceUntilIdle()

        val dialogState = viewModel.dialogState.value
        assertTrue(dialogState is DialogTypes.SimpleError)
        assertEquals(errorMessage, (dialogState as DialogTypes.SimpleError).message)
    }

    @Test
    fun `fetchFourDaysForecast - Unavailable failure shows error dialog`() = runTest {
        val errorMessage = "Four day forecast service down"
        coEvery { getFourDayForecastUseCase(any()) } returns
                DomainResult.Failure.Unavailable(errorMessage)

        viewModel.fetchFourDaysForecast()
        advanceUntilIdle()

        val dialogState = viewModel.dialogState.value
        assertTrue(dialogState is DialogTypes.SimpleError)
        assertEquals(errorMessage, (dialogState as DialogTypes.SimpleError).message)
    }

    @Test
    fun `fetchFourDaysForecast - uses selectedDate from current UI state`() = runTest {
        val selectedDate = LocalDate.of(2025, 1, 15)
        viewModel.setSelectedDate(selectedDate)
        advanceUntilIdle()

        coVerify { getFourDayForecastUseCase(selectedDate) }
    }

    @Test
    fun `setSelectedDate - with valid date updates selectedDate in UI state`() = runTest {
        val newDate = LocalDate.of(2025, 6, 20)
        viewModel.setSelectedDate(newDate)
        advanceUntilIdle()

        val state = viewModel.fourDaysForecastUiState.value
        assertEquals(newDate, state.selectedDate)
    }

    @Test
    fun `setSelectedDate - with valid date triggers fetchFourDaysForecast`() = runTest {
        val newDate = LocalDate.of(2025, 6, 20)
        viewModel.setSelectedDate(newDate)
        advanceUntilIdle()

        coVerify { getFourDayForecastUseCase(newDate) }
    }

    @Test
    fun `setSelectedDate - with null date does not update state or trigger fetch`() = runTest {
        advanceUntilIdle() // let init settle

        val stateBefore = viewModel.fourDaysForecastUiState.value

        viewModel.setSelectedDate(null)
        advanceUntilIdle()

        val stateAfter = viewModel.fourDaysForecastUiState.value
        assertEquals(stateBefore.selectedDate, stateAfter.selectedDate)
        // The use case should not have been called with a null date explicitly
        coVerify(exactly = 0) { getFourDayForecastUseCase(null) }
    }

    @Test
    fun `onDismissErrorDialog - sets dialogState to null`() = runTest {
        // Trigger an error to populate the dialog state
        coEvery { getTwentyFourHourForecastUseCase(any()) } returns
                DomainResult.Failure.NoData("error")
        viewModel.fetchTwentyFourHoursForecast()
        advanceUntilIdle()

        assertTrue(viewModel.dialogState.value is DialogTypes.SimpleError)

        viewModel.onDismissErrorDialog()

        assertNull(viewModel.dialogState.value)
    }

    @Test
    fun `onDismissErrorDialog - calling when dialog is already null is a no-op`() = runTest {
        advanceUntilIdle()
        assertNull(viewModel.dialogState.value)

        viewModel.onDismissErrorDialog()

        assertNull(viewModel.dialogState.value)
    }

    @Test
    fun `initial twoHoursForecastUiState is default TwoHourForecastUiState`() = runTest {
        advanceUntilIdle()
        assertEquals(TwoHourForecastUiState(), viewModel.twoHoursForecastUiState.value)
    }

    @Test
    fun `initial fourDaysForecastUiState has today as selectedDate`() = runTest {
        advanceUntilIdle()
        val state = viewModel.fourDaysForecastUiState.value
        assertEquals(LocalDate.now(), state.selectedDate)
    }

    @Test
    fun `initial dialogState is null`() = runTest {
        assertNull(viewModel.dialogState.value)
    }
}
