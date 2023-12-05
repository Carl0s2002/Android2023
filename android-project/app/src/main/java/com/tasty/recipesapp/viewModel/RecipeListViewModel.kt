package com.tasty.recipesapp.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.model.InstructionModel
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel: ViewModel() {

    val liveData = MutableLiveData<Array<RecipeModel>>()

    fun readAllRecipes(context: Context){
        viewModelScope.launch {
            val list = RecipeRepository(context).readRecipes()
            val models = list.map {
                val instructionModel = it.instructions.map {
                    InstructionModel(it.display_text , it.position)
                }
                RecipeModel(it.name , it.original_video_url, it.thumbnail_url , instructionModel.toTypedArray())
            }
            liveData.value = models.toTypedArray()
        }
    }

}