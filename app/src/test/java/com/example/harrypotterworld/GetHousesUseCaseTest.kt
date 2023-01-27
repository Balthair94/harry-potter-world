package com.example.harrypotterworld

import com.example.harrypotterworld.remote.HarryService
import com.example.harrypotterworld.remote.HouseRemote
import com.example.harrypotterworld.usecase.GetHousesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetHousesUseCaseTest {

    @ExperimentalCoroutinesApi
    private val dispatcher: TestDispatcher = StandardTestDispatcher()

    private val harryService: HarryService = mock()

    private lateinit var getHousesUseCase: GetHousesUseCase

    @Before
    @ExperimentalCoroutinesApi
    fun setup() {
        getHousesUseCase = GetHousesUseCase(dispatcher, harryService)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getHouses_whenThereAreHouses_shouldBeSuccess() = runTest(dispatcher) {
        val apiHouses = listOf(HouseRemote("", "", "", "", "", "", ""))
        `when`(harryService.getHouses()).thenReturn(apiHouses)

        val state = getHousesUseCase()

        assert(state is GetHousesUseCase.HousesState.Success)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getHouses_whenThereAreNoHouses_shouldBeEmpty() = runTest(dispatcher) {
        val apiHouses = emptyList<HouseRemote>()
        `when`(harryService.getHouses()).thenReturn(apiHouses)

        val state = getHousesUseCase()

        assert(state is GetHousesUseCase.HousesState.Empty)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getHouses_whenApiCallFails_shouldBeError() = runTest(dispatcher) {
        `when`(harryService.getHouses()).thenThrow(UnsupportedOperationException::class.java)

        val state = getHousesUseCase()

        assert(state is GetHousesUseCase.HousesState.Error)
    }
}