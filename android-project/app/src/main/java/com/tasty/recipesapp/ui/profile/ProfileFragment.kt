package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.AuthenticationManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.connections.RecipeDatabase
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.entities.RecipeEntity
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.MyRecipeListAdapter
import com.tasty.recipesapp.utils.JSONConverter
import com.tasty.recipesapp.viewModel.ProfileViewModel
import com.tasty.recipesapp.viewModel.ProfileViewModelFactory

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
            navController.navigate(R.id.profile_to_recipe_details , bundleOf("ownRecipeId" to it.id.toString()))
        }

        myAdapter.onClickListenerDelete = {
            val json = JSONConverter().convertToJson(it)
            val userId = AuthenticationManager(requireContext()).getUserId()
            if (userId != null ) {
                val recipeEntity = RecipeEntity(it.id, json.toString(), userId , true)
                viewModel.deleteRecipe(requireContext(), recipeEntity)
            }
            viewModel.getMyRecipes(requireContext())
        }

        binding.floatingButton.setOnClickListener{
            navController.navigate(R.id.profile_to_new_recipe_fragment)
        }

        binding.FavoritesButton.setOnClickListener {
            navController.navigate(R.id.profile_to_favorites )
        }

        return binding.root
    }

}