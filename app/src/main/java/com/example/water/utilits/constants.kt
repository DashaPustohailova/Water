package com.example.water.utilits

import com.example.water.presentation.MainActivity
import com.example.water.data.repository.DatabaseRepositoryImpl
import com.example.water.data.storage.firebase.FirebaseStorage
import com.example.water.domain.repository.DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity
var REPOSITORY: DatabaseRepository = DatabaseRepositoryImpl(FirebaseStorage())

