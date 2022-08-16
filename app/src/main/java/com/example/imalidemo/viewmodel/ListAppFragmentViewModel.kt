package com.example.imalidemo.viewmodel

import android.app.Activity
import android.app.Application
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imalidemo.entity.AppEntity
import com.example.imalidemo.repositories.AppListRepository
import com.example.imalidemo.repositories.FirebaseRepository
import kotlinx.coroutines.*
import kotlin.system.exitProcess

class ListAppFragmentViewModel(application: Application) : ViewModel() {
    private val appListRepository = AppListRepository(application)
    private val firebaseRepository= FirebaseRepository(application)
    var appList  = MutableLiveData<List<AppEntity>>(ArrayList())
    init {
        getAllApp()

    }

    private fun getAllApp(){
       appList.value=appListRepository.getInstalledApp()
        val uid =firebaseRepository.getUserUid()
        firebaseRepository.insertUserApplicationOntoFirebase(uid, appList.value!!)
    }
     fun signOut(view: View){

        viewModelScope.launch {
            firebaseRepository.signOutUser()
            delay(500)
            if(firebaseRepository.getCurrentUser().not()) exitProcess(0)
            Log.d("exit","I am quitting")
            Log.d("exit","I want to quit")
        }
    }
}