package com.tasty.recipesapp.ui.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.AuthenticationManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.connections.RecipeDatabase
import com.tasty.recipesapp.databinding.FragmentFavoriteRecipesBinding
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.ui.profile.IngredientsInputListAdapter
import com.tasty.recipesapp.ui.profile.InstructionsInputListAdapter
import com.tasty.recipesapp.ui.recipe.MyRecipeListAdapter
import com.tasty.recipesapp.utils.JSONConverter
import com.tasty.recipesapp.viewModel.FavoriteRecipesFragmentViewModel
import com.tasty.recipesapp.viewModel.FavoriteRecipesFragmentViewModelFactory
import com.tasty.recipesapp.viewModel.ProfileViewModel
import com.tasty.recipesapp.viewModel.ProfileViewModelFactory
import com.tasty.recipesapp.viewModel.RecipeDetailViewModel
import com.tasty.recipesapp.viewModel.RecipeDetailViewModelFactory
import org.json.JSONArray
import org.json.JSONObject


class FavoriteRecipesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteRecipesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteRecipesBinding.inflate(inflater , container , false)

        val recipeId = arguments?.getString("recipeId")

        val myAdapter = MyRecipeListAdapter(emptyArray())
        binding.FavoriteRecipesList.layoutManager = LinearLayoutManager(requireContext())
        binding.FavoriteRecipesList.adapter = myAdapter

        val recipeDao = RecipeDatabase.getDatabase(requireContext()).recipeDao()

        val viewModel: FavoriteRecipesFragmentViewModel by viewModels{ FavoriteRecipesFragmentViewModelFactory(recipeDao) }

        val liveData = viewModel.liveData
        liveData.observe(viewLifecycleOwner) {recipes ->
            myAdapter.myRecipes = recipes
            myAdapter.notifyDataSetChanged()
        }

        viewModel.getMyRecipes(requireContext())

        if ( recipeId != null ){
            val recipeDetailViewModel: RecipeDetailViewModel by viewModels{ RecipeDetailViewModelFactory(recipeDao) }
            val profileViewModel: ProfileViewModel by viewModels{ ProfileViewModelFactory(recipeDao) }

            recipeDetailViewModel.getRecipeById(recipeId , requireContext())

            val recipeLiveData = recipeDetailViewModel.liveData
            recipeLiveData.observe(viewLifecycleOwner) { recipe ->


                Log.d("RecipeInFavorites", recipe.toString())

                val json = JSONObject()
                json.put("name", recipe.title)
                json.put("original_video_url", recipe.video)
                json.put("thumbnail_url", recipe.thumbnail)
                json.put("description", recipe.description)

                val instructionsArray = JSONArray()
                recipe.instructions.forEach {
                        instruction ->
                    val instructionJson = JSONObject()
                    instructionJson.put("position" , instruction.position)
                    instructionJson.put("display_text" , instruction.display_text)
                    instructionsArray.put(instructionJson)
                }
                json.put("instructions", instructionsArray)

                val sectionsArray = JSONArray()
                val componentsArray = JSONArray()

                recipe.sections.forEach {
                    it.components.forEach { component ->
                        val componentObject = JSONObject()
                        val ingredientJson = JSONObject()
                        componentObject.put("position", component.position)
                        ingredientJson.put("name", component.ingredient.name)
                        componentObject.put("ingredient", ingredientJson)
                        componentsArray.put(componentObject)
                        Log.d("ingredientDetails", componentsArray.toString())
                    }
                }
                val sectionObject = JSONObject()
                sectionObject.put("components", componentsArray)
                sectionsArray.put(sectionObject)
                json.put("sections", sectionsArray)


                val userId = AuthenticationManager(requireContext()).getUserId()
                if (userId != null) {

                    profileViewModel.insertRecipe(
                        requireContext(),
                        RecipeEntity(recipeId.toLong(), json.toString(), userId, false)
                    )
                }
            }
            viewModel.getMyRecipes(requireContext())
        }

        myAdapter.onClickListenerDelete ={
            val profileViewModel: ProfileViewModel by viewModels{ ProfileViewModelFactory(recipeDao) }

            val userId = AuthenticationManager(requireContext()).getUserId()
            if (userId != null ) {
                val json = JSONConverter().convertToJsonForDeletion(it)
                val recipeEntity = RecipeEntity(it.id, json.toString(), userId , false)
                profileViewModel.deleteRecipe(requireContext(), recipeEntity)
            }
        }

        return binding.root
    }


}