package com.tasty.recipesapp.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel: ViewModel() {

    val liveData = MutableLiveData<Array<RecipeModel>>()

    fun readAllRecipes(context: Context){
        viewModelScope.launch {
            val list = RecipeRepository(context).readRecipes()
            val models = list.map {
                RecipeModel(it.name)
            }
            liveData.value = models.toTypedArray()
        }
    }

}