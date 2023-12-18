package com.tasty.recipesapp.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.tasty.recipesapp.AuthenticationManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRegisterBinding
import com.tasty.recipesapp.User
import java.util.UUID


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater , container , false)
        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.RegisterButton.setOnClickListener {
            if (binding.ConfirmPasswordInput.text.toString() == binding.PasswordInput.text.toString() && !binding.PasswordInput.text.isEmpty() && !binding.UserNameInput.text.isEmpty()) {
                val authenticationManager = AuthenticationManager(requireContext())
                val user = User(UUID.randomUUID().toString() , binding.UserNameInput.text.toString()  , binding.PasswordInput.text.toString())
                authenticationManager.registerUser(user)
                navController.navigate(R.id.register_to_login_fragment)
                }
            else {
                Toast.makeText(requireContext() , "Something went wrong" , Toast.LENGTH_SHORT).show()
            }
            }

        binding.BackToLoginButton.setOnClickListener {
            navController.navigate(R.id.register_to_login_fragment)
        }
        return binding.root
    }


}