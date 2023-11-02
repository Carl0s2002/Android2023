package com.tasty.recipesapp.data.models

data class InstructionModel(
    val id: Int,
    val display_text:
    String,
    val time: InstructionTime
)

data class InstructionTime(
    val start_time: Int,
    val end_time: Int
)