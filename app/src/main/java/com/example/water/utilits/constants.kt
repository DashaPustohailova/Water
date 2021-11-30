package com.example.water.utilits

import com.example.water.MainActivity
import com.example.water.database.AppDatabaseRepository
import com.example.water.database.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlin.properties.Delegates

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_ID: String
var REF_DATABASE: DatabaseReference ?= null
lateinit var APP_ACTIVITY: MainActivity
lateinit var EMAIL: String
lateinit var PASSWORD: String
var REPOSITORY: DatabaseRepository = AppDatabaseRepository()
var INIT_DATABASE = false
const val NAME = "name"
const val WEIGHT = "weight"
const val GENDER = "gender"