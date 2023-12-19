package com.tasty.recipesapp.data.dtos

data class RecipeDTO(
    val id: Long ,
    val name: String ,
    val description: String ,
    val original_video_url: String? ,
    val thumbnail_url: String? ,
    val instructions: Array<InstructionDTO> ,
    val sections: Array<SectionsDTO> ,
    val isUserCreated: Boolean
)
