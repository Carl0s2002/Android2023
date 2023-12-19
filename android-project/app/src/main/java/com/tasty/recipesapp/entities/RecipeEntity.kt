package com.tasty.recipesapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MyOwnRecipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    @SerializedName("json_data")
    val json: String ,
    val userId:String ,
    val isUserCreated:Boolean
)
