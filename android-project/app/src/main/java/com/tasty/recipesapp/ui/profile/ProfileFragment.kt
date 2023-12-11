package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.connections.RecipeDatabase
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.MyRecipeListAdapter
import com.tasty.recipesapp.ui.recipe.RecipeListAdapter
import com.tasty.recipesapp.viewModel.ProfileViewModel
import com.tasty.recipesapp.viewModel.ProfileViewModelFactory
import com.tasty.recipesapp.viewModel.RecipeListViewModel
import com.tasty.recipesapp.viewModel.RecipeListViewModelFactory
import org.json.JSONArray
import org.json.JSONObject

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentProfileBinding.inflate(inflater , container , false)
        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_container) as NavHostFragment
        val navController = navHostFragment.navController

        val recipes = emptyArray<RecipeModel>()
        val myAdapter = MyRecipeListAdapter(recipes)
        binding.recycleRandomRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleRandomRecipes.adapter = myAdapter

        val recipeDao = RecipeDatabase.getDatabase(requireContext()).recipeDao()
        val viewModel: ProfileViewModel by viewModels{ ProfileViewModelFactory(recipeDao) }

        val liveData = viewModel.liveData
        liveData.observe(viewLifecycleOwner) {recipes ->
            myAdapter.myRecipes = recipes
            myAdapter.notifyDataSetChanged()
        }

        viewModel.getMyRecipes(requireContext())

        myAdapter.onClickListener = {
            navController.navigate(R.id.profile_to_recipe_details , bundleOf("recipe" to it))
        }

        myAdapter.onClickListenerDelete = {
            val json = JSONObject()
            json.put("id" , it.id)
            json.put("name" , it.title.toString())
            json.put("original_video_url" , it.video.toString())
            json.put("thumbnail_url" , it.thumbnail.toString())
            json.put("description" , it.description.toString())

            val instructionsArray = JSONArray()
            it.instructions.forEach {
                 instruction ->
                    val instructionJson = JSONObject()
                    instructionJson.put("position" , instruction.position)
                    instructionJson.put("display_text" , instruction.display_text)
                    instructionsArray.put(instructionJson)
                }
            json.put("instructions" , instructionsArray)

            val sectionsArray = JSONArray()
            val componentsArray = JSONArray()

            it.sections.forEach {
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
            val recipeEntity = RecipeEntity(it.id , json.toString())
            viewModel.deleteRecipe(requireContext() , recipeEntity)
            viewModel.getMyRecipes(requireContext())
        }

        binding.floatingButton.setOnClickListener{
            navController.navigate(R.id.profile_to_new_recipe_fragment)
        }

        return binding.root
    }
}