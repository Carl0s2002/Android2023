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
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.RecipeListAdapter
import com.tasty.recipesapp.viewModel.RecipeListViewModel

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
        val myAdapter = RecipeListAdapter(recipes)
        binding.recycleRandomRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleRandomRecipes.adapter = myAdapter

        val viewModel: RecipeListViewModel by viewModels()

        val liveData = viewModel.liveData
        liveData.observe(viewLifecycleOwner) {recipes ->
            val randomRecipes = recipes.toList().shuffled().take(3) // Shuffle and take 3 random recipes
            myAdapter.recipes = randomRecipes.toTypedArray()
            myAdapter.notifyDataSetChanged()
        }

        viewModel.readAllRecipes(requireContext())

        myAdapter.onClickListener = {
            navController.navigate(R.id.profile_to_recipe_details , bundleOf("recipe" to it))
        }

        return binding.root
    }
}