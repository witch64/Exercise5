package com.example.exercise5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var counterViewModel: CounterViewModel
    lateinit var sharedPreferences : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate")
        //Initialize the counter ViewModel
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //Initialise the shared preferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        imageViewLike.setOnClickListener {
            counterViewModel.plusLike()
            textViewLike.text = counterViewModel.likeCount.toString()
        }

        imageViewDislike.setOnClickListener {
            counterViewModel.plusDislike()
            textViewDislike.text = counterViewModel.dislikeCount.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity", "onResume")

        val like = sharedPreferences.getInt(getString(R.string.like), 0)
        val dislike = sharedPreferences.getInt(getString(R.string.dislike), 0)

        counterViewModel.likeCount = like
        counterViewModel.dislikeCount = dislike

        textViewLike.text = counterViewModel.likeCount.toString()
        textViewDislike.text = counterViewModel.dislikeCount.toString()

        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "onPause")

        //To store the data of like and dislike
        with(sharedPreferences.edit()) {
            putInt(getString(R.string.like), counterViewModel.likeCount)
            putInt(getString(R.string.dislike), counterViewModel.dislikeCount)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy")
        super.onDestroy()
    }
}