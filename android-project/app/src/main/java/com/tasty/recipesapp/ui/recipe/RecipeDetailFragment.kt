package com.tasty.recipesapp.ui.recipe

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.tasty.recipesapp.connections.RecipeDatabase
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.model.ComponentsModel
import com.tasty.recipesapp.viewModel.RecipeDetailViewModel
import com.tasty.recipesapp.viewModel.RecipeDetailViewModelFactory

class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    private lateinit var player: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ,
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater , container , false)

        val recipeId = arguments?.getString("recipeId")

        val recipeDao = RecipeDatabase.getDatabase(requireContext()).recipeDao()

        val viewModel: RecipeDetailViewModel by viewModels{ RecipeDetailViewModelFactory(recipeDao) }

        val liveData = viewModel.liveData

        liveData.observe(viewLifecycleOwner) { recipe ->
            binding.exampleInput.text = recipe?.title
            player = ExoPlayer.Builder(requireContext()).build()
            player.setMediaItem(MediaItem.Builder().setUri(Uri.parse(recipe?.video.toString())).build());
            player.prepare()
            player.play()
            binding.instructionVideo.player = player
            Log.d("RecipeDetailFragment" , recipe.toString())
            if ( recipe?.instructions != null ) {
                val instructions = recipe.instructions
                val myAdapter = InstructionsListAdapter(requireContext() , instructions)
                binding.instructionsList.adapter = myAdapter

                myAdapter.instructions = instructions

            }

            var ingredientsArray = emptyArray<ComponentsModel>()

            if ( recipe?.sections != null ) {
                recipe.sections.forEach {
                    it.components.forEach {
                        ingredientsArray =
                            ingredientsArray.plus(ComponentsModel(it.ingredient, it.position))
                    }
                }
                val myAdapter = IngredientListAdapter(requireContext() , ingredientsArray)
                binding.ingredientsList.adapter = myAdapter
            }

        }

        if (recipeId != null) {
            viewModel.getRecipeById(recipeId , requireContext())
        }
        else {
            val ownRecipeId = arguments?.getString("ownRecipeId")
            if ( ownRecipeId != null ){
                viewModel.getOwnRecipeById(ownRecipeId.toLong() , requireContext())
            }
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
    }

}