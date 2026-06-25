package com.liuyuheng.sgdata.carparkavailability.presentation.carparkavailability

import app.cash.turbine.test
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkInfo
import com.liuyuheng.sgdata.carparkavailability.domain.models.CarparkType
import com.liuyuheng.sgdata.carparkavailability.domain.models.ParkingSystemType
import com.liuyuheng.sgdata.carparkavailability.domain.usecases.GetCarparkAvailabilityUseCase
import com.liuyuheng.sgdata.core.domain.models.DomainResult
import com.liuyuheng.sgdata.core.presentation.components.dialog.DialogTypes
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
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

@OptIn(ExperimentalCoroutinesApi::class)
class CarparkAvailabilityViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    // Mocks
    private val getCarparkAvailabilityUseCase: GetCarparkAvailabilityUseCase = mockk()

    // Shared Flow to emit mock values to the UI state stream
    private val carparkAvailabilityStream = MutableSharedFlow<DomainResult<List<CarparkInfo>>>()

    private lateinit var viewModel: CarparkAvailabilityViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getCarparkAvailabilityUseCase.fetchCarparkAvailability() } returns Unit
        every { getCarparkAvailabilityUseCase.getCarparkAvailabilityStream() } returns carparkAvailabilityStream
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun initViewModel() {
        viewModel = CarparkAvailabilityViewModel(getCarparkAvailabilityUseCase)
    }

    @Test
    fun `init - starts with Idle and transitions to Loading, then calls fetch`() = runTest {
        initViewModel()

        // Initial state before dispatcher runs
        assertEquals(CarparkAvailabilityUiState.Idle, viewModel.carparkParkAvailabilityUiState.value)

        // Advance dispatcher to execute the init coroutine block
        advanceUntilIdle()

        assertEquals(CarparkAvailabilityUiState.Loading, viewModel.carparkParkAvailabilityUiState.value)
        coVerify(exactly = 1) { getCarparkAvailabilityUseCase.fetchCarparkAvailability() }
    }

    @Test
    fun `stream emits Success updates uiState to Loaded`() = runTest {
        initViewModel()

        viewModel.carparkParkAvailabilityUiState.test {
            // First item replayed on subscription
            assertEquals(CarparkAvailabilityUiState.Idle, awaitItem())

            advanceUntilIdle()
            assertEquals(CarparkAvailabilityUiState.Loading, awaitItem())

            val mockData = listOf(
                CarparkInfo(
                    carparkId = "CP123",
                    address = "Test Road",
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
            )

            // Emit success result
            carparkAvailabilityStream.emit(DomainResult.Success(mockData))
            advanceUntilIdle()

            val state = awaitItem()
            assertTrue(state is CarparkAvailabilityUiState.Loaded)
            assertEquals(mockData, (state as CarparkAvailabilityUiState.Loaded).data)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `stream emits NoData failure updates uiState to Error and shows dialog`() = runTest {
        initViewModel()

        viewModel.carparkParkAvailabilityUiState.test {
            assertEquals(CarparkAvailabilityUiState.Idle, awaitItem())
            advanceUntilIdle()
            assertEquals(CarparkAvailabilityUiState.Loading, awaitItem())

            val errorMessage = "No carpark data found"
            carparkAvailabilityStream.emit(DomainResult.Failure.NoData(errorMessage))
            advanceUntilIdle()

            val state = awaitItem()
            assertTrue(state is CarparkAvailabilityUiState.Error)
            assertEquals(errorMessage, (state as CarparkAvailabilityUiState.Error).message)

            val dialog = viewModel.dialogState.value
            assertTrue(dialog is DialogTypes.SimpleError)
            assertEquals(errorMessage, (dialog as DialogTypes.SimpleError).message)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `stream emits Unavailable failure updates uiState to Error and shows dialog`() = runTest {
        initViewModel()

        viewModel.carparkParkAvailabilityUiState.test {
            assertEquals(CarparkAvailabilityUiState.Idle, awaitItem())
            advanceUntilIdle()
            assertEquals(CarparkAvailabilityUiState.Loading, awaitItem())

            val errorMessage = "Service temporarily unavailable"
            carparkAvailabilityStream.emit(DomainResult.Failure.Unavailable(errorMessage))
            advanceUntilIdle()

            val state = awaitItem()
            assertTrue(state is CarparkAvailabilityUiState.Error)
            assertEquals(errorMessage, (state as CarparkAvailabilityUiState.Error).message)

            val dialog = viewModel.dialogState.value
            assertTrue(dialog is DialogTypes.SimpleError)
            assertEquals(errorMessage, (dialog as DialogTypes.SimpleError).message)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onDismissErrorDialog resets dialogState to null`() = runTest {
        initViewModel()
        advanceUntilIdle()

        val errorMessage = "Error message"
        carparkAvailabilityStream.emit(DomainResult.Failure.NoData(errorMessage))
        advanceUntilIdle()

        // Verify dialog is shown
        assertTrue(viewModel.dialogState.value is DialogTypes.SimpleError)

        // Dismiss the dialog
        viewModel.onDismissErrorDialog()
        advanceUntilIdle()

        assertNull(viewModel.dialogState.value)
    }
}
