package com.tasty.recipesapp.model


data class RecipeModel(
    val id: Long ,
    val title: String? ,
    val description: String? ,
    val video: String? ,
    val thumbnail: String? ,
    val instructions: Array<InstructionModel> ,
    val sections: Array<SectionsModel> )

