package com.tasty.recipesapp.data.dtos

data class RecipeDTO(
    val name: String ,
    val instructions: Array<InstructionDTO>
)
