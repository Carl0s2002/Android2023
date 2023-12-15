package com.tasty.recipesapp.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.dao.RecipeDao
import com.tasty.recipesapp.model.ComponentsModel
import com.tasty.recipesapp.model.IngredientModel
import com.tasty.recipesapp.model.InstructionModel
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.model.SectionsModel
import com.tasty.recipesapp.repo.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val recipeDao: RecipeDao):ViewModel() {

    val liveData = MutableLiveData<RecipeModel>()
    fun getRecipeById(id:String , context: Context){
        viewModelScope.launch {
            val recipe = RecipeRepository( context , recipeDao).getRecipeByIdFromApi(id)
            if (recipe != null) {
                val instructionModel = recipe.instructions.map {
                    InstructionModel(it.display_text, it.position)
                }
                val sectionsModel = recipe.sections.map {
                    SectionsModel(
                        it.components.map {
                            val ingredientName = it.ingredient?.name ?: "Unknown Ingredient"
                            ComponentsModel(IngredientModel(ingredientName), it.position)
                        }.toTypedArray()
                    )
                }.toTypedArray() ?: emptyArray()


               liveData.value =  RecipeModel(recipe.id , recipe.name , recipe.description , recipe.original_video_url , recipe.thumbnail_url , instructionModel.toTypedArray() , sectionsModel)

            }
        }
    }

}