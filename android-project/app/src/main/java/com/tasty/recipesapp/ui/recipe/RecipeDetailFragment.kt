package com.tasty.recipesapp.ui.recipe

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.model.ComponentsModel
import com.tasty.recipesapp.model.InstructionModel
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.viewModel.RecipeListViewModel

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
        val recipe = arguments?.getParcelable<RecipeModel>("recipe")
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
                if ( it.components != null ){
                    it.components.forEach {
                        if ( it.ingredient != null ) {
                            ingredientsArray =
                                ingredientsArray.plus(ComponentsModel(it.ingredient, it.position))
                        }
                    }
                }
            }
            val myAdapter = IngredientListAdapter(requireContext() , ingredientsArray)
            binding.ingredientsList.adapter = myAdapter
        }


        binding.exampleInput.text = recipe?.title
        Log.d("RecipeDetailFragment" , recipe?.video.toString())
        player = ExoPlayer.Builder(requireContext()).build()
        player.setMediaItem(MediaItem.Builder().setUri(Uri.parse(recipe?.video.toString())).build());
        player.prepare()
        player.play()
        binding.instructionVideo.player = player
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