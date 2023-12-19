package com.tasty.recipesapp

import android.content.Context
import android.content.SharedPreferences
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class AuthenticationManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun registerUser(user: User) {
        val userList: MutableList<User> = getUserList()
        userList.add(user)
        saveUserList(userList)
    }

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

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getString("userId", null) != null
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }

    fun logoutUser() {
        editor.clear()
        editor.apply()
    }

    private fun getUserByUsername(username: String): User? {
        val userList = getUserList()
        return userList.find { it.userName == username }
    }

    private fun getUserList(): MutableList<User> {
        val userListJson = sharedPreferences.getString("userList", null)
        return if (userListJson != null) {
            Gson().fromJson(userListJson, object : TypeToken<MutableList<User>>() {}.type)
        } else {
            mutableListOf()
        }
    }

    private fun saveUserList(userList: List<User>) {
        val userListJson = Gson().toJson(userList)
        editor.putString("userList", userListJson)
        editor.apply()
    }
}


