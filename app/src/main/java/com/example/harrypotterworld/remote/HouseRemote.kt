package com.example.harrypotterworld.remote

import com.google.gson.annotations.SerializedName


data class HouseRemote(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("houseColours")
    val houseColours: String,
    @SerializedName("founder")
    val founder: String,
    @SerializedName("animal")
    val animal: String,
    @SerializedName("element")
    val element: String,
    @SerializedName("ghost")
    val ghost: String
)
