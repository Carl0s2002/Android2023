package com.tasty.recipesapp.service

import com.tasty.recipesapp.data.dtos.RecipeDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: 76e5e48316msh0580540a2471d09p10a3dcjsnef2079f038d0" ,
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String ,
        @Query("size") size: String ,
        @Query("tags") tags: String? = null
    ): Array<RecipeDTO>
}