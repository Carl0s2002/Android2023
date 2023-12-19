package com.tasty.recipesapp.viewModel

import android.content.Context
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

class ProfileViewModel(private val recipeDao: RecipeDao): ViewModel() {

    val liveData = MutableLiveData<Array<RecipeModel>>()

    fun insertRecipe( context: Context , recipeEntity: RecipeEntity ){
        viewModelScope.launch {
            RecipeRepository(context , recipeDao).insertRecipe(recipeEntity )
        }
    }

    fun deleteRecipe( context: Context , recipeEntity: RecipeEntity){
        viewModelScope.launch {
            RecipeRepository(context , recipeDao).deleteRecipe(recipeEntity)
        }
    }
    fun getMyRecipes(context: Context){
        viewModelScope.launch {
            val list = RecipeRepository(context, recipeDao).readRecipesFromRoom()
            val filteredList = list.filter { it.isUserCreated }
            val models = filteredList.map {
                val instructionModel = it.instructions.map {
                    InstructionModel(it.display_text , it.position)
                }
                val sectionsModel = it.sections.map{
                    SectionsModel(
                        it.components.map{
                            val ingredientName = it.ingredient?.name ?: "Unknown Ingredient"
                            ComponentsModel(IngredientModel(ingredientName) , it.position)
                        }.toTypedArray() ?: emptyArray()
                    )
                }.toTypedArray() ?: emptyArray()
                RecipeModel(it.id ,it.name , it.description , it.original_video_url, it.thumbnail_url , instructionModel.toTypedArray() , sectionsModel)
            }
            liveData.value = models.toTypedArray()
        }
    }
}