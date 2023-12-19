package com.tasty.recipesapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.getStartedButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.home_to_recipes)
        }
        binding.logoutButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.home_to_login)
        }
        return binding.root
    }


}