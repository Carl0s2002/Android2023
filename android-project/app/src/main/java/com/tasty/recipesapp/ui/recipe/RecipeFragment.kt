package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.data.dtos.RecipeDTO
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
        val navController = findNavController()
//        binding.buttonToRecipeDetails.setOnClickListener {
//            navController.navigate(R.id.recipe_to_all_recipe_details)
//        }

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

        return binding.root
    }
}