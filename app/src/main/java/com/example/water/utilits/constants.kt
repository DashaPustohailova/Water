package com.example.water.utilits

import com.example.water.presentation.MainActivity
import com.example.water.data.repository.DatabaseRepositoryImpl
import com.example.water.data.storage.firebase.FirebaseStorage
import com.example.water.domain.repository.DatabaseRepository
import com.example.water.domain.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var AUTH: FirebaseAuth
lateinit var APP_ACTIVITY: MainActivity
lateinit var EMAIL: String
lateinit var PASSWORD: String

var ID_REPORT:String? = ""
var REPOSITORY: DatabaseRepository = DatabaseRepositoryImpl(FirebaseStorage())
var INIT_DATABASE = false
var USER_NAME: String = ""
var USER_DATA: UserData = UserData()
var CURRENT_ID: String = ""
var REF_DATABASE: DatabaseReference ?= null

const val NAME = "name"
const val WEIGHT = "weight"
const val GENDER = "gender"
const val NORM_WATER = "normWater"
const val DATE = "date"
const val WATER = "water"