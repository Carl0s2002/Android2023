package com.tasty.recipesapp.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.dtos.RecipeDTO
import com.tasty.recipesapp.model.RecipeModel

class RecipeListAdapter(var recipes: Array<RecipeModel>):RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    var onClickListener: ((RecipeModel) -> Unit)? = null
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView

        init {
            title = itemView.findViewById(R.id.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = recipes[position].title

        holder.itemView.setOnClickListener{
                onClickListener?.invoke(recipes[position])
        }

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

}