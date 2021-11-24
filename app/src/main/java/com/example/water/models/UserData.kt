package com.example.water.models

import java.io.Serializable

data class UserData(
    val name: String,
    val gender: String,
    val weight: Int,
    val normWater: Int
):Serializable
