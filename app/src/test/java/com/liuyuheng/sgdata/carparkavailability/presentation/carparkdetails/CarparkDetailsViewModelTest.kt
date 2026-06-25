package com.liuyuheng.sgdata.carparkavailability.presentation.carparkdetails

import androidx.compose.ui.text.input.TextFieldValue
import app.cash.turbine.test
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkType
import com.liuyuheng.sgdata.carparkavailability.domain.models.ParkingSystemType
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.GetCarparkDetailsUseCase
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.UpdateCarparkDetailsUseCase
import com.liuyuheng.sgdata.core.domain.usecases.GetMetadataUseCase
import com.liuyuheng.sgdata.core.utils.toDisplayDate
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalCoroutinesApi::class)
class CarparkDetailsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    // Mocks
    private val getMetadataUseCase: GetMetadataUseCase = mockk()
    private val getCarparkDetailsUseCase: GetCarparkDetailsUseCase = mockk()
    private val updateCarparkDetailsUseCase: UpdateCarparkDetailsUseCase = mockk()

    // SUT
    private lateinit var viewModel: CarparkDetailsViewModel

    // Test Data
    private val testDateTime = LocalDateTime.of(2026, 6, 25, 12, 0)
    private val testCarparkList = listOf(
        createMockCarparkInfo("CP1", "Block 123 Bukit Batok"),
        createMockCarparkInfo("CP2", "Block 456 Jurong West"),
        createMockCarparkInfo("CP3", "Block 789 Bukit Timah")
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        coEvery { getMetadataUseCase() } returns testDateTime
        every { getCarparkDetailsUseCase() } returns flowOf(testCarparkList)
        every { updateCarparkDetailsUseCase() } returns Unit

        viewModel = CarparkDetailsViewModel(
            getMetadataUseCase = getMetadataUseCase,
            getCarparkDetailsUseCase = getCarparkDetailsUseCase,
            updateCarparkDetailsUseCase = updateCarparkDetailsUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state emits initial values and updates with usecase data`() = runTest {
        viewModel.carparkDetailsUiState.test {
            // Initial state from stateIn (initialValue = CarparkDetailsUiState())
            assertEquals(CarparkDetailsUiState(), awaitItem())

            // Advance to allow flow combinations to run
            advanceUntilIdle()

            val updatedState = awaitItem()
            assertEquals(testCarparkList, updatedState.filteredCarparkInfoList)
            assertEquals("", updatedState.queryString.text)
            assertEquals(testDateTime.toDisplayDate(), updatedState.lastUpdated)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onQueryStringChanged immediately updates query string but delays list filtering`() = runTest {
        viewModel.carparkDetailsUiState.test {
            // Consume initial state and updated state
            assertEquals(CarparkDetailsUiState(), awaitItem())
            advanceUntilIdle()
            val baseState = awaitItem()
            assertEquals(testCarparkList, baseState.filteredCarparkInfoList)

            // Update query string
            viewModel.onQueryStringChanged(TextFieldValue("Bukit"))

            // Query string should update immediately in the next emission, but list is not filtered yet (due to debounce)
            val queryUpdatedState = awaitItem()
            assertEquals("Bukit", queryUpdatedState.queryString.text)
            assertEquals(testCarparkList, queryUpdatedState.filteredCarparkInfoList)

            // Advance time by 499 ms (just below 500 ms debounce)
            advanceTimeBy(499.milliseconds)
            // No new emission should occur yet
            expectNoEvents()

            // Advance time to pass the 500 ms threshold
            advanceTimeBy(5.milliseconds)
            advanceUntilIdle()

            val filteredState = awaitItem()
            assertEquals("Bukit", filteredState.queryString.text)
            // Should contain "Bukit Batok" (CP1) and "Bukit Timah" (CP3)
            assertEquals(2, filteredState.filteredCarparkInfoList.size)
            assertTrue(filteredState.filteredCarparkInfoList.any { it.carparkId == "CP1" })
            assertTrue(filteredState.filteredCarparkInfoList.any { it.carparkId == "CP3" })

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onQueryStringChanged filters carpark list case-insensitively by carparkId or address`() = runTest {
        viewModel.carparkDetailsUiState.test {
            // Consume initial state and updated state
            assertEquals(CarparkDetailsUiState(), awaitItem())
            advanceUntilIdle()
            awaitItem()

            // Query matching carparkId case-insensitively
            viewModel.onQueryStringChanged(TextFieldValue("cp2"))
            advanceUntilIdle() // consuming debounce

            // Consume the intermediate and final items
            val states = cancelAndConsumeRemainingEvents()
                .filterIsInstance<app.cash.turbine.Event.Item<CarparkDetailsUiState>>()
                .map { it.value }

            // The last state in the sequence should contain only CP2
            val lastState = states.last()
            assertEquals(1, lastState.filteredCarparkInfoList.size)
            assertEquals("CP2", lastState.filteredCarparkInfoList[0].carparkId)
        }
    }

    @Test
    fun `updateCarparkInfoDataset invokes updateCarparkDetailsUseCase`() = runTest {
        viewModel.updateCarparkInfoDataset()
        advanceUntilIdle()
        verify(exactly = 1) { updateCarparkDetailsUseCase() }
    }

    private fun createMockCarparkInfo(carparkId: String, address: String): CarparkInfo {
        return CarparkInfo(
            carparkId = carparkId,
            address = address,
            coordinates = CarparkInfo.Coordinates(0.0, 0.0),
            carparkType = CarparkType.entries.first(),
            parkingSystemType = ParkingSystemType.entries.first(),
            shortTermParkingTiming = "",
            freeParkingTiming = "",
            nightParkingAvailable = false,
            carparkDecks = 1,
            gantryHeight = 0.0,
            carparkBasementAvailable = false
        )
    }
}
