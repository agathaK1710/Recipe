package com.android.recipe.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.android.recipe.data.database.AppDatabase
import com.android.recipe.data.network.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    fun loadData() {
        val disposable = ApiFactory.apiService.searchRecipeByName(name = "pasta")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG", it.toString())
            }, {
                it.message?.let { it1 -> Log.d("TAG", it1) }
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}