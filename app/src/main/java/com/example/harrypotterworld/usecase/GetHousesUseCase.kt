package com.example.harrypotterworld.usecase

import com.example.harrypotterworld.CoroutinesModule
import com.example.harrypotterworld.model.House
import com.example.harrypotterworld.remote.HarryService
import com.example.harrypotterworld.remote.HouseRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHousesUseCase @Inject constructor(
    @CoroutinesModule.DispatcherIO
    private val dispatcher: CoroutineDispatcher,
    private val harryService: HarryService
) {
    suspend fun invoke(): List<House> = withContext(dispatcher) {
        return@withContext harryService.getHouses().map { it.mapRemoteToLocal() }
    }

    private fun HouseRemote.mapRemoteToLocal() = House(
        this.name,
        this.founder,
        this.houseColours,
        this.animal
    )
}