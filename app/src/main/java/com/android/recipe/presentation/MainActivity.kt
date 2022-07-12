package com.android.recipe.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.recipe.R
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[RecipeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModel.loadData()
//        viewModel.getRecipeInfo(654959).observe(this){
//            Log.d("MainActivity", "$it")
//        }

        applicationContext.deleteDatabase("app.db")

    }
}