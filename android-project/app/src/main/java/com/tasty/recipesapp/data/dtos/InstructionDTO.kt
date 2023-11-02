package com.tasty.recipesapp.data.dtos

data class InstructionDTO (
    val appliance: String?,
    val end_time: Int,
    val temperature: String?,
    val id: Int,
    val position: Int,
    val display_text: String,
    val start_time: Int
)