package com.example.imalidemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.imalidemo.repositories.FirebaseRepository
import com.example.imalidemo.activities.AppGrid

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebaseRepository = FirebaseRepository(application)

        val result =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                Log.d("user","Inside activity")
                if (it.resultCode == Activity.RESULT_OK) {
                    firebaseRepository.firebaseAuthWithGoogle(it.data!!)
                    Log.d("user", "Logged In")
                    val intent= Intent(this, AppGrid::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Need to sign in with google",Toast.LENGTH_LONG).show()
                    Log.d("user",ActivityResult.resultCodeToString(it.resultCode))
                }
            }
        Handler(mainLooper).postDelayed(
            {
                if (firebaseRepository.getCurrentUser()) {
                    Log.d("user", "user exist")
                    val intent= Intent(this, AppGrid::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    result.launch(firebaseRepository.signInUsingGoogle())

                    Log.d("Login", "Not Logged in")
                }
            }, 100
        )
    }

}