package com.tasty.recipesapp.ui.recipe

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
import com.tasty.recipesapp.databinding.FragmentRecipeBinding
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.viewModel.RecipeListViewModel


class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater , container , false)
        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_container) as NavHostFragment
        val navController = navHostFragment.navController

        val recipes: Array<RecipeModel> = emptyArray()
        val myAdapter = RecipeListAdapter(recipes)
        binding.recycleRecipes.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleRecipes.adapter = myAdapter

        val viewModel: RecipeListViewModel by viewModels()

        val liveData = viewModel.liveData
        liveData.observe(viewLifecycleOwner) {
            myAdapter.recipes = it
            myAdapter.notifyDataSetChanged()
            it.forEach {
                Log.d("RecipeFragment" , it.toString())
            }
        }
        viewModel.readAllRecipes(requireContext())

        myAdapter.onClickListener = {
            navController.navigate(R.id.recipe_to_all_recipe_details , bundleOf("recipe" to it))
        }

        return binding.root
    }
}