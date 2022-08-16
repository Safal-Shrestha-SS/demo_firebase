package com.example.imalidemo.repositories

import android.app.Application
import android.content.pm.PackageManager
import com.example.imalidemo.entity.AppEntity

class AppListRepository (private val application: Application){

    fun getInstalledApp(): List<AppEntity>{
        val list = application.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val listOfAppEntity: MutableList<AppEntity> = ArrayList()
            for (packageInfo in list ){
                val name = application.packageManager.getApplicationLabel(packageInfo)
                val image= application.packageManager.getApplicationIcon(packageInfo.packageName)
               listOfAppEntity.add(AppEntity(name.toString(),packageInfo.packageName, image))
            }
        return  listOfAppEntity
    }

}