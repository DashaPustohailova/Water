package com.example.water.database

import com.example.water.R
import com.example.water.models.UserData
import com.example.water.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppDatabaseRepository : DatabaseRepository{

    private val mAuth = FirebaseAuth.getInstance()
    override fun connectToDatabase(onSuccess: () -> Unit, onFail: () -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_registrateFragment)}
    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun registration(userData: UserData, onSuccess: () -> Unit ) {

        mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
//                val mDatabaseReference = FirebaseDatabase.getInstance().reference
//                    .child(mAuth.currentUser?.uid.toString())
//                val idUserData =
//                    mDatabaseReference.ref?.child("userData")
//                        .push().key.toString()
//                val userDataNote = hashMapOf<String, Any>()
//                userDataNote[NAME] = userData.name
//                userDataNote[GENDER] = userData.gender
//                userDataNote[WEIGHT] = userData.weight
//
//                mDatabaseReference.child("userData/${idUserData}")
//                    .updateChildren(userDataNote)
//                    .addOnSuccessListener { onSuccess() }
                onSuccess()
            }
    }
}