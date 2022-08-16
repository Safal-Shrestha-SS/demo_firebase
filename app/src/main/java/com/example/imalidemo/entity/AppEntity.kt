package com.example.imalidemo.entity

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon

data class AppEntity(
    val appName: String,
    var packageName: String,
    val appIcon: Drawable,
    var locked: Boolean =true
)
