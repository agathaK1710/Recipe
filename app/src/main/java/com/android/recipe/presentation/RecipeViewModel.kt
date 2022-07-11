package com.android.recipe.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.recipe.data.database.AppDatabase
import com.android.recipe.data.database.mapper.RecipeMapper
import com.android.recipe.data.network.ApiFactory
import com.android.recipe.data.network.model.RecipeDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    private val mapper = RecipeMapper()

    val recipeList = db.recipeDao().getRecipeList()

    fun loadData() {
//        val disposable = ApiFactory.apiService.searchRecipeByName(name = "pasta")
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                Log.d("TAG", it.foods.toString())
//                val recipeDtoList = mapper.mealListDtoToRecipeListDto(it.foods)
//                db.recipeDao().insertRecipeList(recipeDtoList)
//            }, {
//                it.message?.let { it1 -> Log.d("TAG", it1) }
//            })
        val disposable = ApiFactory.apiService.getRecipeInformation(id = 654959)
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("TAG", it.toString())
            }, {
                it.message?.let { it1 -> Log.d("TAG", it1) }
            })
        compositeDisposable.add(disposable)
    }

    fun getRecipeInfo(id: Int): LiveData<RecipeDto> {
        return db.recipeDao().getRecipeById(id)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}