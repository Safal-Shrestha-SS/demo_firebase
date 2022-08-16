package com.example.imalidemo.repositories

import android.app.Application
import android.content.Intent
import android.util.Log
import com.example.imalidemo.R
import com.example.imalidemo.entity.AppEntity
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirebaseRepository(private val application: Application) {
    private lateinit var gsc: GoogleSignInClient
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val fireDb: FirebaseFirestore by lazy{
       Firebase.firestore
    }
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(application.getString(R.string.default_web_client_id)) //you can also use R.string.default_web_client_id
        .requestEmail()
        .build()

    fun signInUsingGoogle(): Intent {
        gsc = GoogleSignIn.getClient(application, gso)
        return gsc.signInIntent
    }

    fun firebaseAuthWithGoogle(data: Intent): Task<AuthResult> {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        return auth.signInWithCredential(credential)
    }

    fun getCurrentUser(): Boolean {
        val user = auth.currentUser
        if (user != null) return true
        return false
    }
    fun getUserUid(): String {
        return auth.currentUser?.uid!!
    }
    fun insertUserApplicationOntoFirebase(uid:String,listOfApp:List<AppEntity>){
        val mapOfApp = listOfApp.associate { it.packageName to it.locked }
        Log.d("map",mapOfApp.toString())
        fireDb.collection("users").document(uid).set(mapOfApp)
    }
    fun signOutUser() {
        gsc = GoogleSignIn.getClient(application, gso)
        gsc.signOut()
        auth.signOut()
        return
    }
}