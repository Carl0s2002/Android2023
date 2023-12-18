package com.tasty.recipesapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tasty.recipesapp.entities.RecipeEntity

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipeEntity: RecipeEntity )

    @Query("SELECT * FROM MyOwnRecipes WHERE id = :id")
    suspend fun getRecipeById(id: Long): RecipeEntity?

    @Query("SELECT * FROM MyOwnRecipes")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Delete
    suspend fun deleteRecipe(recipeEntity: RecipeEntity)

}