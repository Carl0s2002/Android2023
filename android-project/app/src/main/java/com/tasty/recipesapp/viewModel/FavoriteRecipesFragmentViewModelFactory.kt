package com.tasty.recipesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.dao.RecipeDao

class FavoriteRecipesFragmentViewModelFactory(private val recipeDao: RecipeDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteRecipesFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteRecipesFragmentViewModel(recipeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}