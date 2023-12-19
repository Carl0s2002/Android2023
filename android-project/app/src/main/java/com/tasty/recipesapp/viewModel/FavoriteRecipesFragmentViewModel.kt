package com.tasty.recipesapp.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.dao.RecipeDao
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.model.ComponentsModel
import com.tasty.recipesapp.model.IngredientModel
import com.tasty.recipesapp.model.InstructionModel
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.model.SectionsModel
import com.tasty.recipesapp.repo.RecipeRepository
import kotlinx.coroutines.launch

class FavoriteRecipesFragmentViewModel(private val recipeDao: RecipeDao): ViewModel() {

    val liveData = MutableLiveData<Array<RecipeModel>>()

    fun getMyRecipes(context: Context){
        viewModelScope.launch {
            val list = RecipeRepository(context, recipeDao).readRecipesFromRoom()
            val filteredModels = list.filter { !it.isUserCreated }
            val models = filteredModels.map {
                    Log.d("RecipesLocal" , it.toString())
                    val instructionModel = it.instructions.map {
                        InstructionModel(it.display_text, it.position)
                    }
                    val sectionsModel = it.sections.map {
                        SectionsModel(
                            it.components.map {
                                val ingredientName = it.ingredient?.name ?: "Unknown Ingredient"
                                ComponentsModel(IngredientModel(ingredientName), it.position)
                            }.toTypedArray() ?: emptyArray()
                        )
                    }.toTypedArray() ?: emptyArray()
                    RecipeModel(
                        it.id,
                        it.name,
                        it.description,
                        it.original_video_url,
                        it.thumbnail_url,
                        instructionModel.toTypedArray(),
                        sectionsModel
                    )
            }
            liveData.value = models.toTypedArray()
        }
    }

}