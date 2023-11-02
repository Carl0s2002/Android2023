package com.tasty.recipesapp.recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tasty.recipesapp.data.dtos.InstructionDTO

class RecipeListViewModel: ViewModel() {

    init {
        fetchInstructionData()
    }

    val gson = Gson() ;
    fun fetchInstructionData() {
        val jsonString = """{\n" +
                "\"appliance\": null,\n" +
                "\"end_time\": 26500,\n" +
                "\"temperature\": null,\n" +
                "\"id\": 43381,\n" +
                "\"position\": 1,\n" +
                "\"display_text\": \"In a blender or food processor, combine the yogurt, lime\n" +
                "juice, pepper, and chili powder and pulse to combine. Add ½ of the avocado and\n" +
                "blend until creamy.\",\n" +
                "\"start_time\": 7000\n" +
                "}""" ;

        val test = gson.fromJson(jsonString , InstructionDTO::class.java)
        Log.i("GSON" , test.toString())
    }
}