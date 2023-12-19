package com.tasty.recipesapp.utils

import android.util.Log
import com.tasty.recipesapp.model.RecipeModel
import org.json.JSONArray
import org.json.JSONObject

class JSONConverter {

    fun convertToJsonForDeletion(recipe: RecipeModel): JSONObject {
        val json = JSONObject()
        json.put("id" , recipe.id)
        json.put("name" , recipe.title.toString())
        json.put("original_video_url" , recipe.video.toString())
        json.put("thumbnail_url" , recipe.thumbnail.toString())
        json.put("description" , recipe.description.toString())

        val instructionsArray = JSONArray()
        recipe.instructions.forEach {
                instruction ->
            val instructionJson = JSONObject()
            instructionJson.put("position" , instruction.position)
            instructionJson.put("display_text" , instruction.display_text)
            instructionsArray.put(instructionJson)
        }
        json.put("instructions" , instructionsArray)

        val sectionsArray = JSONArray()
        val componentsArray = JSONArray()

        recipe.sections.forEach {
            it.components.forEach { component ->
                val componentObject = JSONObject()
                val ingredientJson = JSONObject()
                componentObject.put("position" , component.position)
                ingredientJson.put("name" , component.ingredient.name)
                componentObject.put("ingredient" , ingredientJson)
                componentsArray.put(componentObject)
                Log.d("ingredientDetails" , componentsArray.toString())
            }
        }
        val sectionObject = JSONObject()
        sectionObject.put("components" , componentsArray)
        sectionsArray.put(sectionObject)
        json.put("sections" , sectionsArray)
        return json
    }


}