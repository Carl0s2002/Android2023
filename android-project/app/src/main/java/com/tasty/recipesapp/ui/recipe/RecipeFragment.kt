package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipeBinding


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
        binding.buttonToRecipeDetails.setOnClickListener {
            navController.navigate(R.id.recipe_to_all_recipe_details)
        }
        return binding.root
    }
}