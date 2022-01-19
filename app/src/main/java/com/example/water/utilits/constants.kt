package com.example.water.utilits

import com.example.water.presentation.MainActivity
import com.example.water.data.repository.DatabaseRepositoryImpl
import com.example.water.data.storage.firebase.FirebaseStorage
import com.example.water.domain.repository.DatabaseRepository
import com.example.water.domain.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


lateinit var APP_ACTIVITY: MainActivity

var REPOSITORY: DatabaseRepository = DatabaseRepositoryImpl(FirebaseStorage())
var USER_DATA: UserData = UserData()

