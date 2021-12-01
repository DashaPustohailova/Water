package com.example.water.models

import java.io.Serializable

data class UserData(
    val name: String = "name",
    val gender: String = "gender",
    val weight: Int = 0,
    val normWater: Int = 0
):Serializable
