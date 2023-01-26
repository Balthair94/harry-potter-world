package com.example.harrypotterworld.usecase

import com.example.harrypotterworld.CoroutinesModule.DispatcherIO
import com.example.harrypotterworld.model.House
import com.example.harrypotterworld.remote.HarryService
import com.example.harrypotterworld.remote.HouseRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHousesUseCase @Inject constructor(
    @DispatcherIO
    private val dispatcher: CoroutineDispatcher,
    private val harryService: HarryService
) {

    suspend operator fun invoke(): HousesState = withContext(dispatcher) {
        try {
            val houses = harryService.getHouses().map { it.mapRemoteToLocal() }
            return@withContext if (houses.isEmpty()) {
                HousesState.Empty
            } else {
                HousesState.Success(houses)
            }
        } catch (error: Throwable) {
            error.printStackTrace()
            val errorMessage = error.localizedMessage ?: "Error while getting houses"
            return@withContext HousesState.Error(errorMessage)
        }
    }

    /**
     * Used as example to remember that it is possible to transform the returned value into another
     * object that can fulfill the view/client needs. Or can be processed to be stored in a database
     */
    private fun HouseRemote.mapRemoteToLocal() = House(
        this.name,
        this.founder,
        this.houseColours,
        this.animal
    )

    sealed class HousesState {
        object Idle: HousesState()
        object Loading : HousesState()
        object Empty : HousesState()
        data class Success(val houses: List<House>) : HousesState()
        data class Error(val errorMessage: String) : HousesState()
    }
}