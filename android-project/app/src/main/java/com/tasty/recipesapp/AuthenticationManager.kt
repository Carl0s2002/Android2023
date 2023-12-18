package com.tasty.recipesapp

import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class AuthenticationManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Register a new user
    fun registerUser(user: User) {
        val userList: MutableList<User> = getUserList()
        userList.add(user)
        saveUserList(userList)
    }

    // Simulate user authentication
    fun loginUser(username: String, password: String): Boolean {
        val user = getUserByUsername(username)
        if (user != null && user.password == password) {
            editor.putString("userId", user.id)
            editor.apply()
            return true  // Authentication successful
        } else {
            return false  // Authentication failed
        }
    }

    // Check if the user is logged in
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getString("userId", null) != null
    }

    // Get the logged-in user's ID
    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }

    // Logout the user
    fun logoutUser() {
        editor.clear()
        editor.apply()
    }

    // Get user by username (simulated user retrieval)
    private fun getUserByUsername(username: String): User? {
        val userList = getUserList()
        return userList.find { it.userName == username }
    }

    // Get the list of registered users
    private fun getUserList(): MutableList<User> {
        val userListJson = sharedPreferences.getString("userList", null)
        return if (userListJson != null) {
            Gson().fromJson(userListJson, object : TypeToken<MutableList<User>>() {}.type)
        } else {
            mutableListOf()
        }
    }

    // Save the list of registered users
    private fun saveUserList(userList: List<User>) {
        val userListJson = Gson().toJson(userList)
        editor.putString("userList", userListJson)
        editor.apply()
    }
}


