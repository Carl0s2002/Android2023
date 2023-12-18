package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.AuthenticationManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.connections.RecipeDatabase
import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.viewModel.ProfileViewModel
import com.tasty.recipesapp.viewModel.ProfileViewModelFactory
import org.json.JSONArray
import org.json.JSONObject

class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewRecipeBinding.inflate(inflater , container , false)

        binding.SaveButton.setOnClickListener {
            val json = JSONObject()
            json.put("name" , binding.titleInput.text.toString())
            json.put("original_video_url" , binding.videoUrlInput.text.toString())
            json.put("thumbnail_url" , binding.pictureUrlInput.text.toString())
            json.put("description" , binding.descriptionInput.text.toString())

            val instructionsArray = JSONArray()
            var counter = 1
            binding.instructionsRecyclerView.adapter?.let {
                (it as InstructionsInputListAdapter).instructions.forEach { instruction ->
                    val instructionJson = JSONObject()
                    instructionJson.put("position" , counter++)
                    instructionJson.put("display_text" , instruction)
                    instructionsArray.put(instructionJson)
                }
            }
            json.put("instructions" , instructionsArray)

            val sectionsArray = JSONArray()
            val componentsArray = JSONArray()

            counter = 1
            binding.ingredientsRecyclerView.adapter?.let {
                (it as IngredientsInputListAdapter).ingredients.forEach { ingredient ->
                    val componentObject = JSONObject()
                    val ingredientJson = JSONObject()
                    componentObject.put("position" , counter++)
                    ingredientJson.put("name" , ingredient)
                    componentObject.put("ingredient" , ingredientJson)
                    componentsArray.put(componentObject)
                    Log.d("ingredientDetails" , componentsArray.toString())
                }
            }
            val sectionObject = JSONObject()
            sectionObject.put("components" , componentsArray)
            sectionsArray.put(sectionObject)
            json.put("sections" , sectionsArray)


            val recipeDao = RecipeDatabase.getDatabase(requireContext()).recipeDao()

            val viewModel: ProfileViewModel by viewModels{ ProfileViewModelFactory(recipeDao)  }

            val userId = AuthenticationManager(requireContext()).getUserId()

            if (userId != null) {
                val recipeEntity = RecipeEntity( 0 , json.toString()  ,userId)
                viewModel.insertRecipe(requireContext(), recipeEntity)
            }
            else{
                Toast.makeText(requireContext() , "User not logged in" , Toast.LENGTH_SHORT).show()
            }

            findNavController().navigate(R.id.profile_fragment)

        }

        var i = 1
        val emptyInstructions:MutableList<String> = mutableListOf("#${i++} Enter Instruction")
        val myAdapter = InstructionsInputListAdapter(emptyInstructions)
        binding.instructionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.instructionsRecyclerView.adapter = myAdapter

        var j = 1
        val emptyIngredients:MutableList<String> = mutableListOf("#${j++} Enter Ingredient")
        val myAdapter2 = IngredientsInputListAdapter(emptyIngredients)
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.ingredientsRecyclerView.adapter = myAdapter2

        binding.addInstructionButton.setOnClickListener {
            myAdapter.instructions = (myAdapter.instructions + mutableListOf("#${i++} Enter Instruction")).toMutableList()
            myAdapter.notifyItemInserted(myAdapter.instructions.size - 1)
        }

        binding.addIngredientButton.setOnClickListener {
            myAdapter2.ingredients = (myAdapter2.ingredients + mutableListOf("#${j++} Enter Ingredient")).toMutableList()
            myAdapter2.notifyItemInserted(myAdapter2.ingredients.size - 1)
        }

        return binding.root
    }


}