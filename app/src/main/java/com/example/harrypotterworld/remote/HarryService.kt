package com.example.harrypotterworld.remote

import retrofit2.http.GET

interface HarryService {
    @GET("/Houses")
    suspend fun getHouses(): List<HouseRemote>
}