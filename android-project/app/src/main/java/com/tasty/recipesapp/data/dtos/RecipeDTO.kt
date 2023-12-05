package com.tasty.recipesapp.data.dtos

data class RecipeDTO(
    val name: String ,
    val original_video_url: String? ,
    val thumbnail_url: String? ,
    val instructions: Array<InstructionDTO>
)
