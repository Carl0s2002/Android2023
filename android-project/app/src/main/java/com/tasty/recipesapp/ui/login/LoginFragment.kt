package com.tasty.recipesapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.tasty.recipesapp.AuthenticationManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.User
import com.tasty.recipesapp.databinding.FragmentLoginBinding
import java.util.UUID

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val navHostFragment = requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.RegisterButton.setOnClickListener {
            navController.navigate(R.id.login_to_register_fragment)
        }

        binding.LoginButton.setOnClickListener {
        val authenticationManager = AuthenticationManager(requireContext())
        val user = User(UUID.randomUUID().toString() , binding.UserNameInput.text.toString()  , binding.PasswordInput.text.toString())
        if ( authenticationManager.loginUser(user.userName , user.password) && !binding.UserNameInput.text.isEmpty() && !binding.PasswordInput.text.isEmpty()) {
            navController.navigate(R.id.login_to_home_fragment)
        }
        else {
            Toast.makeText(requireContext(), "Wrong username or password", Toast.LENGTH_SHORT)
                .show()
        }
        }
        return binding.root
    }


}