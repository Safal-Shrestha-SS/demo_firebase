package com.example.imalidemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.imalidemo.R
import com.example.imalidemo.fragments.ListAppFragment

class AppGrid : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_grid)
        val transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.baseFragment, ListAppFragment.newInstance(),null);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}