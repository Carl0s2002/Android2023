package com.tasty.recipesapp.repo

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.tasty.recipesapp.AuthenticationManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.dao.RecipeDao
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.data.dtos.RecipeResultDTO
import com.tasty.recipesapp.entities.RecipeEntity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer


class RecipeRepository(val context: Context , private val recipeDao:RecipeDao) {

    private val recipeApiClient = RecipeApiClient()
    suspend fun insertRecipe(recipeEntity: RecipeEntity ){
        recipeDao.insertRecipe(recipeEntity)
    }

    suspend fun deleteRecipe(recipeEntity: RecipeEntity){
        recipeDao.deleteRecipe(recipeEntity)
    }

    fun readRecipes(): Array<RecipeDTO>{
        val file = context.resources.openRawResource(R.raw.all_recipes)
        val gson = Gson()

        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(file, "UTF-8"))
            val result = gson.fromJson<RecipeResultDTO>(reader , RecipeResultDTO::class.java)
            return result.results
        }
        catch (e: Exception){
            Log.e("Error" , e.message , e )
            return emptyArray()
        }
         finally {
            file.close()
        }
    }

    suspend fun readRecipesFromRoom(): Array<RecipeDTO>{
        val authenticationManager = AuthenticationManager(context)
        val userId = authenticationManager.getUserId()
        val recipeList =  recipeDao.getAllRecipes().filter {
            it.userId == userId
        }
        val ownRecipeList = recipeList.map {
            val jsonObject = JSONObject(it.json)
            Log.d("ReadRecipesFromRoom" , it.json)
            jsonObject.apply { put("id", it.id) }
            val gson = Gson()
            gson.fromJson(jsonObject.toString(), RecipeDTO::class.java)
        }
        return ownRecipeList.toTypedArray()
    }

    suspend fun getRecipeByIdFromRoom(id:Long):RecipeDTO?{
        val recipe = recipeDao.getRecipeById(id)
        if (recipe == null){
            return null
        }
        val jsonObject = JSONObject(recipe.json)
        jsonObject.apply { put("id" , recipe.id) }
        val gson = Gson()
        val recipeDto = gson.fromJson(jsonObject.toString() , RecipeDTO::class.java)
        return recipeDto
    }

    suspend fun getRecipesFromApi(
        from:String ,
        size:String ,
        tags:String? = null
    ): RecipeResultDTO?{
        return recipeApiClient.getRecipes(from , size , tags)
    }

    suspend fun getRecipeByIdFromApi(
        id:String
    ): RecipeDTO?{
        return recipeApiClient.getRecipeById(id)
    }

}
